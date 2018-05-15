package com.example.jack.myapp.wanandroid.activity;

import com.blankj.utilcode.util.AppUtils;
import com.example.jack.myapp.AppConstant;
import com.example.jack.myapp.R;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.TreeBean;
import com.example.jack.myapp.mvp.contract.ArticalListContract;
import com.example.jack.myapp.mvp.presenter.ArticalListPresenter;
import com.example.jack.myapp.wanandroid.BaseActivity;
import com.example.jack.myapp.wanandroid.WanActivity;
import com.example.jack.myapp.wanandroid.adapter.MutipleTypeFragmentAdapter;
import com.example.jack.myapp.wanandroid.fragment.MutipleTypeFragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.jack.myapp.R.string.share_url;

/**
 * Created by lcy on 2018/5/9.
 */

public class MutipleTypeActivity extends BaseActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{
    private Toolbar toolbar;
    private ImageView ivBack;
    private TextView tvKey;
    private ImageView ivShare;
    private ImageView ivSearch;
    private TabLayout tabs;
    private ViewPager viewpager;
    private TreeBean treeBean;
    private List<String> titles =  new ArrayList<>();
    private List<MutipleTypeFragment> fragmentList = new ArrayList<>();
    private int currentPosition = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                // 返回
                MutipleTypeActivity.this.finish();
                break;
            case R.id.iv_search:
                // 搜索
                startActivity(new Intent(MutipleTypeActivity.this, SearchActivity.class));
                break;
            case R.id.iv_share:
                // 分享
                Intent textIntent = new Intent(Intent.ACTION_SEND);
                textIntent.setType("text/plain");
                textIntent.putExtra(Intent.EXTRA_TEXT,
                        getString(R.string.share_url)+treeBean.getChildren().get(currentPosition).getId());
                startActivity(Intent.createChooser(textIntent, "分享"));
                break;
        }
    }


    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        ivBack = findViewById(R.id.iv_back);
        tvKey = findViewById(R.id.tv_key);
        ivShare = findViewById(R.id.iv_share);
        ivSearch = findViewById(R.id.iv_search);
        tabs = findViewById(R.id.tabs);
        viewpager = findViewById(R.id.viewpager);

        viewpager.addOnPageChangeListener(this);
        ivBack.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        ivShare.setOnClickListener(this);


        if (null != getIntent().getSerializableExtra(AppConstant.SERIALIZABLEBEAN)) {
            treeBean = (TreeBean) getIntent().getSerializableExtra(AppConstant.SERIALIZABLEBEAN);
            tvKey.setText(treeBean.getName().toString());
        }

        List<TreeBean.ChildrenBean> children = treeBean.getChildren();
        for (int i = 0; i < children.size() ; i++) {
            String name = children.get(i).getName();
            tabs.addTab(tabs.newTab().setText(name));
            titles.add(name);
            fragmentList.add(MutipleTypeFragment.initialize((long)children.get(i).getId()));
        }

        MutipleTypeFragmentAdapter fragmentAdapter = new MutipleTypeFragmentAdapter(getSupportFragmentManager(), fragmentList, titles);
        viewpager.setAdapter(fragmentAdapter);
        tabs.setupWithViewPager(viewpager);
    }
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mutiple_type;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
