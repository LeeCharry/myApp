package com.example.jack.myapp.wanandroid;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.example.jack.myapp.R;
import com.example.tulib.util.base.XActivity;

/**
 * Created by lcy on 2018/4/18.
 */

public abstract class BaseActivity extends XActivity {
//    protected   Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar();
    }

    private void setToolbar() {
//      toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        if (null == toolbar) {
//            new NullPointerException("toolbar can't be null!");
//        }
//      setSupportActionBar(toolbar);
//      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        /**toolbar除掉阴影*/
//        getSupportActionBar().setElevation(0);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            toolbar.setElevation(0);
//        }
    }
}
