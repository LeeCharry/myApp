package com.example.jack.myapp.wanandroid.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jack.myapp.R;
import com.youth.banner.Banner;

/**
 * Created by lcy on 2018/4/18.
 */

public class HotFragment extends BaseFragment {
    private Banner banner;
    private RecyclerView recyclerview;
    @Override
    protected void initView(View mRootview) {
        banner = (Banner) mRootview.findViewById(R.id.banner);
        recyclerview = (RecyclerView)mRootview. findViewById(R.id.recyclerview);
    }
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_hot;
    }
}
