package com.example.jack.myapp.wanandroid.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.jack.myapp.AppConstant;
import com.example.jack.myapp.R;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.mvp.contract.HotListContract;
import com.example.jack.myapp.mvp.presenter.HotListPresenter;
import com.example.jack.myapp.wanandroid.BaseActivity;
import com.example.jack.myapp.wanandroid.adapter.ArticalAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索界面
 */
public class SearchActivity extends BaseActivity implements HotListContract.View, SwipeRefreshLayout.OnRefreshListener {
    private Toolbar toolbar;
    private ImageView ivClear;
    private EditText etTitle;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private HotListPresenter mPresenter;
    private List<Artical.DatasBean> datasBeanList = new ArrayList<>();
    private int page = 0;
    private String hotKey = "";
    private ArticalAdapter adapter;
    private int totlaPage;

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        ivClear = findViewById(R.id.iv_clear);
        etTitle = findViewById(R.id.et_title);
        refreshLayout = findViewById(R.id.refresh_layout);
        recyclerView = findViewById(R.id.recyclerView);
        etTitle.setHint("搜索...");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.this.finish();
            }
        });

        refreshLayout.setOnRefreshListener(this);

        ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTitle.setText("");
            }
        });

        initAdapter();
        mPresenter = new HotListPresenter(SearchActivity.this, SearchActivity.this);
        setEditorListener();
    }

    /**
     * 键盘搜索事件
     */
    private void setEditorListener() {
        etTitle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE) {
                    if (!etTitle.getText().toString().isEmpty()) {
                        KeyboardUtils.hideSoftInput(etTitle);
                        hotKey = etTitle.getText().toString();
                        datasBeanList.clear();
                        mPresenter.queryByKey(page, hotKey);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void initAdapter() {
        adapter = new ArticalAdapter(datasBeanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        recyclerView.setAdapter(adapter);

        //跳转到文章详情
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position >= 0) {
                    Artical.DatasBean datasBean = datasBeanList.get(position);
                    Intent intent = new Intent(SearchActivity.this, ArticalDetailActivity.class);
                    intent.putExtra(AppConstant.ID,(long)datasBean.getId());
                    intent.putExtra(AppConstant.LINK, datasBean.getLink().toString());
                    intent.putExtra(AppConstant.TITLE, datasBean.getTitle().toString());
                    startActivity(intent);
                }
            }
        });
        //收藏
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.iv_collect) {
                    ToastUtils.showShort("收藏");
                }
            }
        });

        setEmptyView(adapter);
    }

    private void setEmptyView(ArticalAdapter adapter) {
        TextView emptyView = new TextView(SearchActivity.this);
        emptyView.setGravity(Gravity.CENTER);
        emptyView.setText(R.string.nothing);
        adapter.setEmptyView(emptyView);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_hot_key_list;
    }

    @Override
    public void showLoading() {
        loadingDailog.show();
    }

    @Override
    public void hideLoading() {
        loadingDailog.dismiss();
    }

    @Override
    public void showMessage(String msg) {
        ToastUtils.showShort(msg);
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMySelf() {

    }

    @Override
    public void queryByKey(Artical artical) {
        if (artical.getTotal() > 0) {
            totlaPage = artical.getPageCount();
            if (refreshLayout.isRefreshing()) {
                refreshLayout.setRefreshing(false);
                datasBeanList.clear();
            } else {
                adapter.loadMoreComplete();
            }
            datasBeanList.addAll(artical.getDatas());
            adapter.notifyDataSetChanged();
        } else {
            datasBeanList.clear();
            adapter.notifyDataSetChanged();
            showMessage("未搜索到关键词相关文章");
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.queryByKey(page, hotKey);
    }
}
