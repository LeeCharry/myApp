package com.example.jack.myapp.wanandroid.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jack.myapp.R;
import com.example.jack.myapp.bean.HotBean;
import com.example.jack.myapp.mvp.contract.HotContract;
import com.example.jack.myapp.mvp.presenter.HotPresenter;
import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by lcy on 2018/4/18.
 */

public class HotFragment extends BaseFragment implements HotContract.View{
    private Banner banner;
    private RecyclerView recyclerview;
    private HotPresenter mPresenter;
    @Override
    protected void initView(View mRootview) {
        banner = (Banner) mRootview.findViewById(R.id.banner);
        recyclerview = (RecyclerView)mRootview. findViewById(R.id.recyclerview);

        mPresenter = new HotPresenter(context,HotFragment.this);
        mPresenter.getHotKey();
        mPresenter.getFriends();
    }
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_hot;
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
    public void getHotKey(List<HotBean> hotBeanList) {

    }

    @Override
    public void getFriends(List<HotBean> hotBeanList) {

    }
}
