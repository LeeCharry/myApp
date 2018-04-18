package com.example.jack.myapp.wanandroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jack.myapp.R;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.BannerBean;
import com.example.jack.myapp.mvp.contract.HomeContract;
import com.example.jack.myapp.mvp.presenter.HomePresenter;
import com.example.jack.myapp.wanandroid.adapter.ArticalAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 2018/4/18.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View{
    private Banner banner;
    private RecyclerView recyclerview;
    private HomePresenter mPresenter;
    private List<BannerBean> bannerBeanList = new ArrayList<>();
    private List<Artical.DatasBean> articalList = new ArrayList<>();

    private ArticalAdapter articalAdapter;

    @Override
    protected void initView(View mRootview) {
        banner = (Banner) mRootview.findViewById(R.id.banner);
        recyclerview = (RecyclerView)mRootview. findViewById(R.id.recyclerview);

        mPresenter = new HomePresenter(context, HomeFragment.this);
        initBannerData();
        initArticalList();
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
         articalAdapter = new ArticalAdapter(articalList);
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(articalAdapter);
        mPresenter.getArticalist(0);
    }

    private void initBannerData() {
        mPresenter.getBanners();
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
        articalList.clear();
        articalList.addAll(artical.getDatas());
        articalAdapter.notifyDataSetChanged();
    }

    @Override
    public void getBanners(List<BannerBean> bannerBeans) {
        if (bannerBeans.size() > 0) {
//            this.bannerBeanList = bannerBeans;
            bannerBeanList.clear();
            bannerBeanList.addAll(bannerBeans);
            initBanner();
        }
    }
}
