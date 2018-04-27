package com.example.jack.myapp.wanandroid.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.jack.myapp.R;
import com.example.jack.myapp.wanandroid.BaseActivity;

/**
 * hotkey列表详情页
 */
public class HotKeyListActivity extends BaseActivity {
    private Toolbar toolbar;
    private ImageView ivClear;
    private EditText etTitle;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        ivClear = findViewById(R.id.iv_clear);
        etTitle = findViewById(R.id.et_title);
        refreshLayout = findViewById(R.id.refresh_layout);
        recyclerView = findViewById(R.id.recyclerView);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_hot_key_list;
    }
}
