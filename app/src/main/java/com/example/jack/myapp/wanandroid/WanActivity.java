package com.example.jack.myapp.wanandroid;


import android.Manifest;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.AppConstant;
import com.example.jack.myapp.R;
import com.example.jack.myapp.UserManager;
import com.example.jack.myapp.wanandroid.activity.AboutUsActivity;
import com.example.jack.myapp.wanandroid.activity.LoginActivity;
import com.example.jack.myapp.wanandroid.activity.MarkedActivity;
import com.example.jack.myapp.wanandroid.activity.SearchActivity;
import com.example.jack.myapp.wanandroid.fragment.HomeFragment;
import com.example.jack.myapp.wanandroid.fragment.HotFragment;
import com.example.jack.myapp.wanandroid.fragment.KnowLedgeSysFragment;
import com.example.jack.myapp.widget.MD5Util;
import com.trello.rxlifecycle.components.RxFragment;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class WanActivity extends BaseActivity implements View.OnClickListener,
        BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    private static final int LOGIN_FLAG = 100;
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1000;
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
    String md5;
    String path = Environment.getExternalStorageDirectory() + File.separator + "tencent" + File.separator + "2018-05-15.";
    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        framelayout = (FrameLayout) findViewById(R.id.framelayout);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        drawerNav = (NavigationView) findViewById(R.id.nav_view);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        ivHot = (ImageView) findViewById(R.id.iv_hot);
        ivSearch = (ImageView) findViewById(R.id.iv_search);
        setListener();
        initFragments();
        setActionBarIcon();

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
//            //申请WRITE_EXTERNAL_STORAGE权限
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
//
//        }else{
//            md5 = getMd5(path);
//
//            try {
//                String fileMD5String = MD5Util.getFileMD5String(new File(path));
//                LogUtils.a(AppConstant.TAG,fileMD5String);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            String md5Three = getMD5Three(path);
//            LogUtils.a(AppConstant.TAG,md5Three);
//
//            String fileMD5 = MD5Util.getFileMD5(path);
//            LogUtils.a(AppConstant.TAG,fileMD5);
//
//            String md5Hex = null;
//            try {
//                md5Hex = DigestUtils.md5Hex(new FileInputStream(new File(path)));
//                LogUtils.a(AppConstant.TAG,md5Hex);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Log.e("我已有权限","已有权限");
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission has been granted, preview can be displayed
                Log.e("text", "CAMERA permission has now been granted. Showing preview.");
                md5 = getMd5(path);
                try {
                    String fileMD5String = MD5Util.getFileMD5String(new File(path));
                    LogUtils.a(AppConstant.TAG,fileMD5String);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String md5Three = getMD5Three(path);
                LogUtils.a(AppConstant.TAG,md5Three);

                String fileMD5 = MD5Util.getFileMD5(path);
                LogUtils.a(AppConstant.TAG,fileMD5);

                String md5Hex = null;
                try {
                     md5Hex = new String(Hex.encodeHex(DigestUtils.md5(new FileInputStream(new File(path)))));
//                   md5Hex = DigestUtils.md5Hex(new FileInputStream(new File(path)));
                    LogUtils.a(AppConstant.TAG,md5Hex);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                Log.e("text", "CAMERA permission was NOT granted.");

            }

        }
    }

    private String getMd5(String path) {
        try {

            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            BigInteger bigInt = new BigInteger(1, md.digest());
            String md5 = bigInt.toString(16);
//            Log.d("lcy", "getMd5: "+s);
            LogUtils.a(AppConstant.TAG,md5);

//            byte[] bytes = EncodeUtils.base64Encode(md5);
//            String str2 = EncodeUtils.base64Encode2String(md5.getBytes());
//
//            LogUtils.a(AppConstant.TAG,bytes.length+"\n     "+bytes.toString());
//            LogUtils.a(AppConstant.TAG,str2);

            return md5;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }


    public static String getMD5Three(String path) {
//        DigestUtils.md5Hex(new FileInputStream(path));

               BigInteger bi = null;
               try {
                       byte[] buffer = new byte[8192];
                       int len = 0;
                       MessageDigest md = MessageDigest.getInstance("MD5");
                       File f = new File(path);
                       FileInputStream fis = new FileInputStream(f);
                       while ((len = fis.read(buffer)) != -1) {
                             md.update(buffer, 0, len);
                         }
                        fis.close();
                        byte[] b = md.digest();
                        bi = new BigInteger(1, b);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                return bi.toString(16);
            }



//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.item_home:
//                    toolbar.setTitle("玩Android");
//                    switchFragment(0);
//                    break;
//                case R.id.item_knowledge_sys:
//                    toolbar.setTitle("知识体系");
//                    switchFragment(1);
//                    break;
//                case R.id.item_like:
//                    ToastUtils.showShort("我喜欢的");
//                    onBackPressed();
//                    break;
//                case R.id.item_about_us:
//                    ToastUtils.showShort("关于我们");
//                    onBackPressed();
//                    break;
//                default:
//                    break;
//            }
//            return false;
//        }
//    };


    private void setListener() {
        navigation.setOnNavigationItemSelectedListener(this);
        drawerNav.setNavigationItemSelectedListener(this);

        View headerView = drawerNav.getHeaderView(0);
        tvLogin = (TextView) headerView.findViewById(R.id.tv_login);
        tvUsername = (TextView) headerView.findViewById(R.id.tv_username);
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
        fragmentList.add(new HomeFragment());
        fragmentList.add(new KnowLedgeSysFragment());
        fragmentList.add(new HotFragment());
       switchFragment(0);
    }

    /**
     * 切换fragment
     *
     * @param position
     */
    private void switchFragment(int position) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        RxFragment currentFrgment = fragmentList.get(position);
        if (position != lastPosition) {
            ft.hide(fragmentList.get(lastPosition));
        }
        if (!currentFrgment.isAdded()) {
            ft.add(R.id.framelayout, currentFrgment);
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
                toolbar.setTitle(R.string.home);
                switchFragment(0);
                return true;
            case R.id.item_knowledge_sys:
                toolbar.setTitle(R.string.knowledge);
                switchFragment(1);
                return true;
            case R.id.item_like:
                startActivity(new Intent(WanActivity.this, MarkedActivity.class));
                onBackPressed();
                return true;
            case R.id.item_about_us:
//                ToastUtils.showShort("关于我们");
                startActivity(new Intent(WanActivity.this, AboutUsActivity.class));
                onBackPressed();
                return true;
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
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (UserManager.isLogin()){
            if (null != UserManager.getUserBean()) {
                tvUsername.setText( UserManager.getUserBean().getUsername());
                tvLogin.setText("退出登录");
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                if (!UserManager.isLogin()){
                    startActivity(new Intent(WanActivity.this, LoginActivity.class));
                }else{
                    logout();
                }
                break;
            case R.id.iv_hot:
                toolbar.setTitle(R.string.hot);
                switchFragment(2);
                break;
            case R.id.iv_search:
                startActivity(new Intent(WanActivity.this, SearchActivity.class));
                break;
        }
    }

    /**
     * 退出登录
     */
    private void logout() {
        UserManager.setLogin(false);
        tvLogin.setText("点击登录");
        tvUsername.setText("未登录");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
