package com.example.jack.myapp.wanandroid.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jack.myapp.R;
/**
 * Created by lcy on 2018/4/18.
 */

public class KnowLedgeSysFragment extends BaseFragment {
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerview;
    @Override
    protected void initView(View mRootview) {
        refreshLayout = (SwipeRefreshLayout)mRootview.findViewById(R.id.refresh_layout);
        recyclerview = (RecyclerView) mRootview.findViewById(R.id.recyclerview);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_knowledge_sys;
    }
}
