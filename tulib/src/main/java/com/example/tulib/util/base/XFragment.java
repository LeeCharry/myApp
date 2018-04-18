package com.example.tulib.util.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.trello.rxlifecycle.components.RxFragment;

/**
 * Created by lcy on 2018/4/8.
 */

public abstract class XFragment extends RxFragment {
    protected View mRootview;
    protected Gson gson;
    protected Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       mRootview = inflater.inflate(getLayoutResId(), container, false);
        context = container.getContext();
       initView(mRootview);
        return mRootview;
    }
    protected abstract void initView(View mRootview);

    protected abstract int getLayoutResId();
}
