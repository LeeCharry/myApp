package com.example.jack.myapp.wanandroid.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.R;
import com.example.jack.myapp.bean.TreeBean;
import com.example.jack.myapp.mvp.contract.TreeContract;
import com.example.jack.myapp.mvp.presenter.TreePresenter;
import com.example.jack.myapp.wanandroid.adapter.TreeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 2018/4/18.
 */

public class KnowLedgeSysFragment extends BaseFragment implements TreeContract.View,SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerview;
    private List<TreeBean>  mTreeList;
    private TreePresenter mPresenter;
    private TreeAdapter adapter;

    @Override
    protected void initView(View mRootview) {
        refreshLayout = (SwipeRefreshLayout)mRootview.findViewById(R.id.refresh_layout);
        recyclerview = (RecyclerView) mRootview.findViewById(R.id.recyclerview);
        refreshLayout.setOnRefreshListener(this);
        mPresenter = new TreePresenter(context,this);
        initAdapter();
        mPresenter.getTree();
    }

    private void initAdapter() {
        mTreeList = new ArrayList<>();
         adapter = new TreeAdapter(mTreeList);
         recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_knowledge_sys;
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
    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMySelf() {

    }

    @Override
    public void getTree(List<TreeBean> treeBean) {
        refreshLayout.setRefreshing(false);
        if (treeBean.size() > 0) {
            mTreeList.clear();
            mTreeList.addAll(treeBean);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getTree();
    }
}
