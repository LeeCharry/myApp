package com.example.jack.myapp.wanandroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.jack.myapp.AppConstant;
import com.example.jack.myapp.R;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.mvp.contract.ArticalListContract;
import com.example.jack.myapp.mvp.contract.MarkedContract;
import com.example.jack.myapp.mvp.presenter.ArticalListPresenter;
import com.example.jack.myapp.mvp.presenter.MarkedPresenter;
import com.example.jack.myapp.wanandroid.activity.ArticalDetailActivity;
import com.example.jack.myapp.wanandroid.activity.HotKeyListActivity;
import com.example.jack.myapp.wanandroid.adapter.ArticalAdapter;
import com.example.jack.myapp.wanandroid.adapter.ArticalAdapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 2018/5/13.
 */

public class MutipleTypeFragment extends Fragment implements ArticalListContract.View,SwipeRefreshLayout.OnRefreshListener,MarkedContract.View{
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private long cid;
    private ArticalAdapter2 adapter;
    private List<Artical.DatasBean> datasBeanList = new ArrayList<>();
    private ArticalListPresenter mPresenter;
    private MarkedPresenter markedPresenter;
    private Context context;
    private int position;

    public void setCid(long cid) {
        this.cid = cid;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootview = inflater.inflate(R.layout.fragment_mutiple_type, null);
        refreshLayout = mRootview.findViewById(R.id.refresh_layout);
        recyclerView = mRootview.findViewById(R.id.recyclerView);
        context = this.getContext();
        refreshLayout.setOnRefreshListener(this);

        initAdapter();

        mPresenter = new ArticalListPresenter(context, MutipleTypeFragment.this);
        markedPresenter = new MarkedPresenter(context, MutipleTypeFragment.this);
        mPresenter.getArticalist(cid);
        return mRootview;
    }
    public static MutipleTypeFragment initialize(long cid) {
        MutipleTypeFragment mutipleTypeFragment = new MutipleTypeFragment();
        mutipleTypeFragment.setCid(cid);
        return mutipleTypeFragment;
    }
    private void initAdapter() {
        adapter = new ArticalAdapter2(datasBeanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

//        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                recyclerView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (page >= totlaPage) {
//                            adapter.loadMoreEnd();
//                        } else {
//                            page++;
//                            mPresenter.queryByKey(page, hotKey);
//                        }
//                    }
//                }, 2000);
//
//            }
//        }, recyclerView);

        //跳转到文章详情
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position >= 0) {
                    Artical.DatasBean datasBean = datasBeanList.get(position);
                    Intent intent = new Intent(context, ArticalDetailActivity.class);
                    intent.putExtra(AppConstant.ID,(long)datasBean.getId());
                    intent.putExtra(AppConstant.LINK,datasBean.getLink().toString());
                    intent.putExtra(AppConstant.TITLE,datasBean.getTitle().toString());
                    launchActivity(intent);

                }
            }
        });
        //收藏
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Artical.DatasBean datasBean = datasBeanList.get(position);
                switch (view.getId()) {
                    case R.id.iv_collect:
                        MutipleTypeFragment.this.position = position;
                        if (!datasBean.isCollect()) {
                            markedPresenter.collectArtical((long) datasBean.getId());
                        } else {
                            markedPresenter.unCollectArtical((long) datasBean.getId(), -1);
                        }
                        break;
                }
            }
        });
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
        startActivity(intent);
    }

    @Override
    public void killMySelf() {

    }

    @Override
    public void getArticalist(Artical artical) {
        refreshLayout.setRefreshing(false);
        datasBeanList.clear();
        datasBeanList.addAll(artical.getDatas());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        mPresenter.getArticalist(cid);
    }

    @Override
    public void getMarkedList(Artical artical) {

    }

    @Override
    public void unCollectArtical() {
        ToastUtils.showShort("取消收藏成功");
        datasBeanList.get(position).setCollect(false);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void collectArtical() {
        ToastUtils.showShort("收藏成功");
        datasBeanList.get(position).setCollect(true);
        adapter.notifyDataSetChanged();
    }
}
