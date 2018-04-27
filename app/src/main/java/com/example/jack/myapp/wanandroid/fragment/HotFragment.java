package com.example.jack.myapp.wanandroid.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.jack.myapp.R;
import com.example.jack.myapp.bean.HotBean;
import com.example.jack.myapp.mvp.contract.HotContract;
import com.example.jack.myapp.mvp.presenter.HotPresenter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 2018/4/18.
 */

public class HotFragment extends BaseFragment implements HotContract.View{
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

        mPresenter = new HotPresenter(context,HotFragment.this);
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

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMySelf() {

    }

    @Override
    public void getHotKey(List<HotBean> hotBeanList) {
            hotKeyList.clear();
            hotKeyList.addAll(hotBeanList);
            initFlowLayout(hotKeyList,fl1);
    }
    private void initFlowLayout(List<HotBean> hotKeyList, final TagFlowLayout fl) {
        fl.setAdapter(new TagAdapter<HotBean>(hotKeyList) {
            @Override
            public View getView(FlowLayout parent, int position, HotBean hotBean) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_hot, fl,false);
                TextView tv = view.findViewById(R.id.tv_name);
                tv.setText(hotBean.getName().toString());
                return tv;
            }
        });
    }

    @Override
    public void getFriends(List<HotBean> hotBeanList) {
        friendList.clear();
        friendList.addAll(hotBeanList);
        initFlowLayout(friendList,fl2);
    }
}
