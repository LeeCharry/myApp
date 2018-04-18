package com.example.jack.myapp.wanandroid;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.R;
import com.example.jack.myapp.wanandroid.activity.LoginActivity;
import com.example.jack.myapp.wanandroid.fragment.HomeFragment;
import com.example.jack.myapp.wanandroid.fragment.HotFragment;
import com.example.jack.myapp.wanandroid.fragment.KnowLedgeSysFragment;
import com.trello.rxlifecycle.components.RxFragment;

import java.util.ArrayList;
import java.util.List;

public class WanActivity extends BaseActivity implements View.OnClickListener,
        BottomNavigationView.OnNavigationItemSelectedListener,NavigationView.OnNavigationItemSelectedListener{
    private FrameLayout framelayout;
    private BottomNavigationView navigation;
    private List<RxFragment> fragmentList;
    private int lastPosition = 0;
    private DrawerLayout drawerlayout;
    private Toolbar toolbar;
    private NavigationView drawerNav;
    private TextView tvUsername;
    private TextView tvLogin;
    private ImageView ivHot;
    private ImageView ivSearch;
    @Override
    protected void initView() {
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        framelayout = (FrameLayout) findViewById(R.id.framelayout);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        drawerNav = (NavigationView) findViewById(R.id.nav_view);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        tvUsername = (TextView) findViewById(R.id.tv_username);
        tvUsername = (TextView) findViewById(R.id.tv_username);
        tvUsername = (TextView) findViewById(R.id.tv_username);
        ivHot = (ImageView) findViewById(R.id.iv_hot);
        ivSearch = (ImageView) findViewById(R.id.iv_search);
        setListener();

        initFragments();
        setActionBarIcon();

    }

    private void setListener() {
        navigation.setOnNavigationItemSelectedListener(this);
        drawerNav.setNavigationItemSelectedListener(this);

        View headerView = drawerNav.getHeaderView(0);
        tvLogin = (TextView) headerView.findViewById(R.id.tv_login);
        tvLogin.setOnClickListener(this);
        ivHot.setOnClickListener(this);
        ivSearch.setOnClickListener(this);

    }
    private void setActionBarIcon() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();

    }
    private void initFragments() {
        fragmentList = new ArrayList<>();
       fragmentList.add( new HomeFragment());
       fragmentList.add( new KnowLedgeSysFragment());
       fragmentList.add( new HotFragment());
       switchFragment(0);
    }
    /**
     * 切换fragment
     * @param position
     */
    private void switchFragment(int position) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        RxFragment currentFrgment = fragmentList.get(position);
        if (position != lastPosition) {
            ft.hide(fragmentList.get(lastPosition));
        }
        if (!currentFrgment.isAdded()) {
            ft.add(R.id.framelayout,currentFrgment);
        }
        ft.show(currentFrgment).commitAllowingStateLoss();
        lastPosition = position;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_wan;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home:
                switchFragment(0);

                break;
            case R.id.item_knowledge_sys:
                switchFragment(1);
                break;
            case R.id.item_like:
                ToastUtils.showShort("我喜欢的");
                onBackPressed();
                break;
            case R.id.item_about_us:
                ToastUtils.showShort("关于我们");
                onBackPressed();
                break;
            default:
                break;
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        if (null == drawerlayout) {
            super.onBackPressed();
            return;
        }
        if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
            drawerlayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                startActivity(new Intent(WanActivity.this, LoginActivity.class));
                break;
            case R.id.iv_hot:
                switchFragment(2);
                break;
            case R.id.iv_search:

                break;
        }
    }
}
