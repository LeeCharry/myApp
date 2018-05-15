package com.example.jack.myapp.wanandroid.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.AppConstant;
import com.example.jack.myapp.R;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.HotBean;
import com.example.jack.myapp.mvp.contract.HotContract;
import com.example.jack.myapp.mvp.presenter.HotPresenter;
import com.example.jack.myapp.wanandroid.activity.ArticalDetailActivity;
import com.example.jack.myapp.wanandroid.activity.HotKeyListActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lcy on 2018/4/18.
 */

public class HotFragment extends BaseFragment implements HotContract.View,SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout refreshLayout;
    private TagFlowLayout fl1;
    private TagFlowLayout fl2;
    private HotPresenter mPresenter;
    private List<HotBean> hotKeyList = new ArrayList<>();
    private List<HotBean> friendList = new ArrayList<>();

    @Override
    protected void initView(View mRootview) {

        refreshLayout = mRootview.findViewById(R.id.refresh_layout);
        fl1 = mRootview.findViewById(R.id.fl1);
        fl2 = mRootview.findViewById(R.id.fl2);
        refreshLayout.setOnRefreshListener(this);

        mPresenter = new HotPresenter(context, HotFragment.this);
        refreshData();

        setOnItemClickListener(fl1);
        setOnItemClickListener(fl2);
    }

    private void setOnItemClickListener(final TagFlowLayout fl) {
        fl.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (parent.equals(fl1)) {
                    intnt2HotListActivity(hotKeyList.get(position).getName());

                }else{
                    //跳转到文章详情页面
                    if (position >= 0) {
                        HotBean hotBean = friendList.get(position);
                        Intent intent = new Intent(context, ArticalDetailActivity.class);
                        intent.putExtra(AppConstant.ID,(long)hotBean.getId());
                        intent.putExtra(AppConstant.LINK,hotBean.getLink().toString());
                        intent.putExtra(AppConstant.TITLE,hotBean.getName().toString());
                        startActivity(intent);
                    }
                }
                return true;
            }
        });
    }

    private void intnt2HotListActivity(String name) {
        Intent intent = new Intent(context, HotKeyListActivity.class);
        intent.putExtra(AppConstant.HOT_KEY,name);
        startActivity(intent);
    }

    private void refreshData() {
        mPresenter.getHotKey();
        mPresenter.getFriends();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_hot;
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

    }

    @Override
    public void killMySelf() {

    }

    @Override
    public void getHotKey(List<HotBean> hotBeanList) {
        refreshLayout.setRefreshing(false);
        hotKeyList.clear();
        hotKeyList.addAll(hotBeanList);
        initFlowLayout(hotKeyList, fl1);
    }

    private void initFlowLayout(List<HotBean> hotKeyList, final TagFlowLayout fl) {
        fl.setAdapter(new TagAdapter<HotBean>(hotKeyList) {
            @Override
            public View getView(FlowLayout parent, int position, HotBean hotBean) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_hot, fl, false);
                TextView tv = view.findViewById(R.id.tv_name);
                setTextColor(tv);
                tv.setText(hotBean.getName().toString());
                return tv;
            }
        });
    }

    /**
     * 随机生成颜色
     * @param tv
     */
    private void setTextColor(TextView tv) {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        tv.setTextColor(Color.rgb(r,g,b));
    }
    @Override
    public void getFriends(List<HotBean> hotBeanList) {
        refreshLayout.setRefreshing(false);
        friendList.clear();
        friendList.addAll(hotBeanList);
        initFlowLayout(friendList, fl2);
    }
    @Override
    public void onRefresh() {
        refreshData();
    }
}
