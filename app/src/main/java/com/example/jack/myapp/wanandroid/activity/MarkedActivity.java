package com.example.jack.myapp.wanandroid.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.jack.myapp.AppConstant;
import com.example.jack.myapp.R;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.TreeBean;
import com.example.jack.myapp.mvp.contract.HotListContract;
import com.example.jack.myapp.mvp.contract.MarkedContract;
import com.example.jack.myapp.mvp.presenter.HotListPresenter;
import com.example.jack.myapp.mvp.presenter.MarkedPresenter;
import com.example.jack.myapp.wanandroid.BaseActivity;
import com.example.jack.myapp.wanandroid.adapter.ArticalAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 收藏列表
 */
public class MarkedActivity extends BaseActivity implements MarkedContract.View, SwipeRefreshLayout.OnRefreshListener {
    private Toolbar toolbar;

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private MarkedPresenter mPresenter;
    private List<Artical.DatasBean> datasBeanList = new ArrayList<>();
    private int page = 0;
    String hotKey = "";
    private ArticalAdapter adapter;
    private int totlaPage;
    private long originId = -1;
//    private boolean isDelete = false;
    public int position;

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        refreshLayout = findViewById(R.id.refresh_layout);
        recyclerView = findViewById(R.id.recyclerView);
        refreshLayout.setOnRefreshListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MarkedActivity.this.finish();
            }
        });

        initAdapter();
        mPresenter = new MarkedPresenter(MarkedActivity.this, MarkedActivity.this);
        mPresenter.getMarkedList(page);

    }


    private void initAdapter() {
        adapter = new ArticalAdapter(datasBeanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MarkedActivity.this));
        recyclerView.setAdapter(adapter);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (page >= totlaPage) {
                            adapter.loadMoreEnd();
                        } else {
                            page++;
                            mPresenter.getMarkedList(page);
                        }
                    }
                }, 2000);

            }
        }, recyclerView);

        //跳转到文章详情
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position >= 0) {
                    Artical.DatasBean datasBean = datasBeanList.get(position);
                    Intent intent = new Intent(MarkedActivity.this, ArticalDetailActivity.class);
                    intent.putExtra(AppConstant.LINK, datasBean.getLink().toString());
                    intent.putExtra(AppConstant.TITLE, datasBean.getTitle().toString());
                    startActivity(intent);
                }
            }
        });
        //取消收藏
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Artical.DatasBean datasBean = datasBeanList.get(position);
                if (view.getId() == R.id.iv_collect) {
                    MarkedActivity.this.position = position;
                    int id = datasBean.getId();
                    mPresenter.unCollectArtical((long) id, originId);
                }else if (view.getId() == R.id.tv_chapter_name){
                    intent2MutipleType(datasBean);
                }
            }
        });
        setEmptyView(adapter);
    }

    private void setEmptyView(ArticalAdapter adapter) {
        TextView emptyView = new TextView(MarkedActivity.this);
        emptyView.setGravity(Gravity.CENTER);
        emptyView.setText(R.string.nothing);
        adapter.setEmptyView(emptyView);
    }

    /**
     * 跳转到MutipleTypeActivity
     * @param datasBean
     */
    private void intent2MutipleType(Artical.DatasBean datasBean) {
        Intent intent = new Intent(this, MutipleTypeActivity.class);
        TreeBean treeBean = new TreeBean();
        treeBean.setName(datasBean.getChapterName());
        List<TreeBean.ChildrenBean> childrenBeans = new ArrayList<>();
        childrenBeans.add(new TreeBean.ChildrenBean(datasBean.getChapterId(),datasBean.getChapterName()));
        treeBean.setChildren(childrenBeans);
        intent.putExtra(AppConstant.SERIALIZABLEBEAN,treeBean);
        startActivity(intent);
    }
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_marked;
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
        refreshLayout.setRefreshing(false);
        ToastUtils.showShort(msg.toString());
    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMySelf() {

    }

    @Override
    public void onRefresh() {
        page = 0;
        mPresenter.getMarkedList(page);
    }
    @Override
    public void getMarkedList(Artical artical) {
        if (artical.getTotal() > 0) {
            totlaPage = artical.getPageCount();
            if (refreshLayout.isRefreshing()) {
                refreshLayout.setRefreshing(false);
                datasBeanList.clear();
            } else {
                adapter.loadMoreComplete();
            }
//            if (isDelete){
//                isDelete = false;
//                datasBeanList.clear();
//            }
            datasBeanList.addAll(artical.getDatas());
            //设置所有isCollect为true
            for (Artical.DatasBean dataBean:datasBeanList) {
                dataBean.setCollect(true);
            }
            adapter.notifyDataSetChanged();
        }
    }
    /**
     * 取消收藏成功
     */
    @Override
    public void unCollectArtical() {
        ToastUtils.showShort(R.string.uncollect_success);
        adapter.remove(position);
//        isDelete = true;
//        onRefresh();
    }

    @Override
    public void collectArtical() {

    }
}
