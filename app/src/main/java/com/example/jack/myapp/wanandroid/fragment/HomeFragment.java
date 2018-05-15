package com.example.jack.myapp.wanandroid.fragment;

import android.content.Context;
import android.content.Intent;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.jack.myapp.AppConstant;
import com.example.jack.myapp.R;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.BannerBean;
import com.example.jack.myapp.bean.TreeBean;
import com.example.jack.myapp.mvp.contract.HomeContract;
import com.example.jack.myapp.mvp.contract.MarkedContract;
import com.example.jack.myapp.mvp.presenter.HomePresenter;
import com.example.jack.myapp.mvp.presenter.MarkedPresenter;
import com.example.jack.myapp.wanandroid.activity.ArticalDetailActivity;
import com.example.jack.myapp.wanandroid.activity.MutipleTypeActivity;
import com.example.jack.myapp.wanandroid.adapter.ArticalAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 * Created by lcy on 2018/4/18.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener, MarkedContract.View {
    private Banner banner;
    private RecyclerView recyclerview;
    private HomePresenter mPresenter;
    private MarkedPresenter markedPresenter;
    private List<BannerBean> bannerBeanList = new ArrayList<>();
    private List<Artical.DatasBean> articalList = new ArrayList<>();

    private ArticalAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private int page = 0;
    private int pageCount = 0;
    private int TOTAL_COUNTER = 0;
    private int mCurrentCounter = 0;
    private int position;

    @Override
    protected void initView(View mRootview) {
        refreshLayout = (SwipeRefreshLayout) mRootview.findViewById(R.id.refresh_layout);
        banner = (Banner) mRootview.findViewById(R.id.banner);
        recyclerview = (RecyclerView) mRootview.findViewById(R.id.recyclerview);

        mPresenter = new HomePresenter(context, HomeFragment.this);
        markedPresenter = new MarkedPresenter(context, HomeFragment.this);
        mPresenter.getBanners();
        initArticalList();
        //下拉加载
        refreshLayout.setOnRefreshListener(this);
        //上拉刷新

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                recyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (page >= pageCount) {
                            adapter.loadMoreEnd();
                        } else {
                            page++;
                            mPresenter.getArticalist(page);
//                            adapter.loadMoreComplete();
                        }
                    }
                }, 2000);
            }
        });
        //收藏
        markArtical();

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Artical.DatasBean datasBean = articalList.get(position);
                switch (view.getId()) {
                    case R.id.iv_collect:
                        HomeFragment.this.position = position;
                        if (!datasBean.isCollect()) {
                            markedPresenter.collectArtical((long) articalList.get(position).getId());
                        } else {
                            markedPresenter.unCollectArtical((long) articalList.get(position).getId(), -1);
                        }
                        break;
                    case R.id.tv_chapter_name:
                        //跳转
                        intent2MutipleType(datasBean);
                        break;
                }

            }
        });
        //跳转到文章详情页

        intent2ArticalDetail();
    }

    /**
     * 跳转到MutipleTypeActivity
     * @param datasBean
     */
    private void intent2MutipleType(Artical.DatasBean datasBean) {
        Intent intent = new Intent(context, MutipleTypeActivity.class);
        TreeBean treeBean = new TreeBean();
        treeBean.setName(datasBean.getChapterName());
        List<TreeBean.ChildrenBean> childrenBeans = new ArrayList<>();
        childrenBeans.add(new TreeBean.ChildrenBean(datasBean.getChapterId(),datasBean.getChapterName()));
        treeBean.setChildren(childrenBeans);
        intent.putExtra(AppConstant.SERIALIZABLEBEAN,treeBean);
        startActivity(intent);
    }

    /**
     * 收藏文章
     */
    private void markArtical() {
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if (view.getId() == R.id.iv_collect) {
                    ToastUtils.showShort("收藏");
                }
            }
        });
    }

    /**
     * 跳转到文章详情页
     */
    private void intent2ArticalDetail() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position >= 0) {
                    Artical.DatasBean datasBean = articalList.get(position);
                    Intent intent = new Intent(context, ArticalDetailActivity.class);
                    intent.putExtra(AppConstant.ID, (long) datasBean.getId());
                    intent.putExtra(AppConstant.LINK, datasBean.getLink().toString());
                    intent.putExtra(AppConstant.TITLE, datasBean.getTitle().toString());
                    startActivity(intent);
                }
            }
        });
    }

    private void initBanner() {
        List<String> bannerTitle = new ArrayList<>();
        List<String> bannerImageUrl = new ArrayList<>();
        for (BannerBean bannerBean :
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
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                LogUtils.a(AppConstant.TAG, position + "");
                if (position >= 0) {
                    BannerBean bannerBean = bannerBeanList.get(position - 1);
                    Intent intent = new Intent(context, ArticalDetailActivity.class);
                    intent.putExtra(AppConstant.LINK, bannerBean.getUrl().toString());
                    intent.putExtra(AppConstant.TITLE, bannerBean.getTitle().toString());
                    startActivity(intent);
                }
            }
        });
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
        ToastUtils.showShort(msg);
        refreshLayout.setRefreshing(false);
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

        if (adapter.isLoading()) {
            //上拉加载
            adapter.loadMoreComplete();
        } else {
            //下拉刷新 & 收藏
            articalList.clear();
            refreshLayout.setRefreshing(false);
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

    @Override
    public void getMarkedList(Artical artical) {

    }

    @Override
    public void unCollectArtical() {
        ToastUtils.showShort("取消收藏成功");
//        onRefresh();
        articalList.get(position).setCollect(false);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void collectArtical() {
        ToastUtils.showShort("收藏成功");
//        onRefresh();
        articalList.get(position).setCollect(true);
        adapter.notifyDataSetChanged();
    }
}
