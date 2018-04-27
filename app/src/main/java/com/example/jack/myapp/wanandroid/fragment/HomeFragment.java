package com.example.jack.myapp.wanandroid.fragment;

import android.content.Context;
import android.content.Intent;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.example.jack.myapp.R;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.BannerBean;
import com.example.jack.myapp.mvp.contract.HomeContract;
import com.example.jack.myapp.mvp.presenter.HomePresenter;
import com.example.jack.myapp.wanandroid.adapter.ArticalAdapter;
import com.example.tulib.util.utils.DeviceUtil;
import com.example.tulib.util.utils.HandlerTip;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 2018/4/18.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View,SwipeRefreshLayout.OnRefreshListener{
    private Banner banner;
    private RecyclerView recyclerview;
    private HomePresenter mPresenter;
    private List<BannerBean> bannerBeanList = new ArrayList<>();
    private List<Artical.DatasBean> articalList = new ArrayList<>();

    private ArticalAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private int page = 0;
    private int pageCount = 0;
    private int TOTAL_COUNTER = 0;
    private int mCurrentCounter = 0;

//     static Boolean isRefresh = true;

    @Override
    protected void initView(View mRootview) {
        refreshLayout = (SwipeRefreshLayout) mRootview.findViewById(R.id.refresh_layout);
        banner = (Banner) mRootview.findViewById(R.id.banner);
        recyclerview = (RecyclerView)mRootview. findViewById(R.id.recyclerview);

        mPresenter = new HomePresenter(context, HomeFragment.this);
        mPresenter.getBanners();
        initArticalList();
        //下拉加载
        refreshLayout.setOnRefreshListener(this);
        //上拉刷新

//        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//
//                if (curPage <= pageCount){
//                    refreshLayout.setEnabled(false);
//                    if (mCurrentCounter >= TOTAL_COUNTER){
//                        adapter.loadMoreEnd(true);
//                    }else{
//                        curPage++;
//                        mPresenter.getArticalist(curPage);
////                        adapter.loadMoreComplete();
//                        refreshLayout.setEnabled(true);
//                    }
//                }
//
//
//            }
//        },recyclerview);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                recyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (page >= pageCount) {
                            adapter.loadMoreEnd();
                        }else{
                            page++;
                            mPresenter.getArticalist(page);
//                            adapter.loadMoreComplete();
                        }
                    }
                },2000);
            }
        });

    }

    private void initBanner() {
         List<String> bannerTitle = new ArrayList<>();
         List<String> bannerImageUrl = new ArrayList<>();
        for (BannerBean bannerBean:
             bannerBeanList) {
            bannerTitle.add(bannerBean.getTitle());
            bannerImageUrl.add(bannerBean.getImagePath());
        }
        banner.setImages(bannerImageUrl)
                .setBannerTitles(bannerTitle)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context)
                                .load(path)
                                .into(imageView);
                    }
                })
                .start();
    }

    private void initArticalList() {
         adapter = new ArticalAdapter(articalList);
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter);
        adapter.setEnableLoadMore(true);
        mPresenter.getArticalist(page);
        adapter.openLoadAnimation();
    }
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }
    @Override
    public void killMySelf() {
    }
    @Override
    public void getArticalist(Artical artical) {
        pageCount = artical.getPageCount();
        if (refreshLayout.isRefreshing()) {
            //下拉刷新
            articalList.clear();
            refreshLayout.setRefreshing(false);
        }else{
            //上拉加载
            adapter.loadMoreComplete();
        }
        articalList.addAll(artical.getDatas());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void getBanners(List<BannerBean> bannerBeans) {
        refreshLayout.setRefreshing(false);
        if (bannerBeans.size() > 0) {
            bannerBeanList.clear();
            bannerBeanList.addAll(bannerBeans);
            initBanner();
        }
    }

    @Override
    public void onRefresh() {
        page = 0;
        mPresenter.getBanners();
        mPresenter.getArticalist(page);
    }
}
