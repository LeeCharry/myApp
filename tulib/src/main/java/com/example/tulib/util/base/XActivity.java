package com.example.tulib.util.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.example.tulib.R;
import com.google.gson.Gson;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


/**
 * Created by lcy on 2018/4/8.
 */

public abstract class XActivity extends RxAppCompatActivity {
    protected Gson gson;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(getLayoutResId());
        gson = new Gson();
//        ButterKnife.bind(this);
        initView();
    }



    protected abstract void initView();

    protected abstract int getLayoutResId();

}
