package com.example.jack.myapp.demo.expandlistview;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.jack.myapp.R;
import com.example.tulib.util.utils.DeviceUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CoordinatorActivity extends AppCompatActivity {
    private String json = "{\"data\":[\n" +
            "{\"groupId\":1,\n" +
            "\"groupName\":\"group1\",\n" +
            "\"childCount\":5,\n" +
            "\"childData\":[\"child0\",\"child1\",\"child2\",\"child3\",\"child4\"]},{\"groupId\":2,\n" +
            "\"groupName\":\"group2\",\n" +
            "\"childCount\":4,\n" +
            "\"childData\":[\"child0\",\"child1\",\"child2\",\"child3\"]},{\"groupId\":3,\n" +
            "\"groupName\":\"group3\",\n" +
            "\"childCount\":6,\n" +
            "\"childData\":[\"child0\",\"child1\",\"child2\",\"child3\",\"child4\",\"child5\"]},\n" +
            "{\"groupId\":3,\n" +
            "\"groupName\":\"group3\",\n" +
            "\"childCount\":6,\n" +
            "\"childData\":[\"child0\",\"child1\",\"child2\",\"child3\",\"child4\",\"child5\"]},\n" +
            "{\"groupId\":3,\n" +
            "\"groupName\":\"group3\",\n" +
            "\"childCount\":6,\n" +
            "\"childData\":[\"child0\",\"child1\",\"child2\",\"child3\",\"child4\",\"child5\"]},\n" +
            "{\"groupId\":3,\n" +
            "\"groupName\":\"group3\",\n" +
            "\"childCount\":6,\n" +
            "\"childData\":[\"child0\",\"child1\",\"child2\",\"child3\",\"child4\",\"child5\"]},\n" +
            "{\"groupId\":3,\n" +
            "\"groupName\":\"group3\",\n" +
            "\"childCount\":6,\n" +
            "\"childData\":[\"child0\",\"child1\",\"child2\",\"child3\",\"child4\",\"child5\"]},\n" +
            "{\"groupId\":3,\n" +
            "\"groupName\":\"group3\",\n" +
            "\"childCount\":6,\n" +
            "\"childData\":[\"child0\",\"child1\",\"child2\",\"child3\",\"child4\",\"child5\"]},\n" +
            "{\"groupId\":3,\n" +
            "\"groupName\":\"group3\",\n" +
            "\"childCount\":6,\n" +
            "\"childData\":[\"child0\",\"child1\",\"child2\",\"child3\",\"child4\",\"child5\"]},\n" +
            "{\"groupId\":3,\n" +
            "\"groupName\":\"group3\",\n" +
            "\"childCount\":6,\n" +
            "\"childData\":[\"child0\",\"child1\",\"child2\",\"child3\",\"child4\",\"child5\"]},\n" +
            "{\"groupId\":3,\n" +
            "\"groupName\":\"group3\",\n" +
            "\"childCount\":6,\n" +
            "\"childData\":[\"child0\",\"child1\",\"child2\",\"child3\",\"child4\",\"child5\"]}]}";
    private CoordinatorLayout mainContent;
    private AppBarLayout appbar;
    private Toolbar toolbar;
    private TabLayout tabs;
    private ViewPager viewpager;
    private FloatingActionButton fab;


    private List<GroupBean.DataBean> dataBeanList = new ArrayList<>();
    private String[] titles = {"JAVA","ANDROID","IOS","KOTLIN","TYPESCRIPT"} ;
    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandlistview);
        DeviceUtil.setImmersiveStatus(this);
        initview();
        initViewpager();
        initData();
        ExpandAdapter expandAdapter = new ExpandAdapter(dataBeanList);
//        expandLv.setAdapter(expandAdapter);
//        expandLv.setGroupIndicator(null);
//        expandLv.setChildIndicator(null);
//        for (int i = 0; i < dataBeanList.size() ; i++) {
//            expandLv.expandGroup(i);
//        }
    }

    private void initViewpager() {
       List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i <tabs.getTabCount() ; i++) {
            fragments.add(MyFragment.getMyFragmentInstance(tabs.getTabAt(i).getText().toString(),tabs.getTabCount()+i+20));
        }
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewpager.setAdapter(adapter);
        tabs.setupWithViewPager(viewpager);
    }

    private void initview() {
        mainContent = findViewById(R.id.main_content);
        appbar = findViewById(R.id.appbar);
        toolbar = findViewById(R.id.toolbar);
        tabs = findViewById(R.id.tabs);
        viewpager = findViewById(R.id.viewpager);
        fab = findViewById(R.id.fab);
//      recyclerview = findViewById(R.id.recyclerView);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoordinatorActivity.this.finish();
            }
        });
        for (int i = 0; i < titles.length ; i++) {
            tabs.addTab(tabs.newTab().setText(titles[i]));
        }

//        initRV();
    }

    private void initRV() {
        List<String> datas = new ArrayList<>();

        for (int i = 0; i < 50 ; i++) {
            datas.add("JAVA"+i);
        }
        RvAdapter rvAdapter = new RvAdapter(datas);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(rvAdapter);
        recyclerview.setNestedScrollingEnabled(false);
    }

    private void initData() {
        GroupBean groupBean = new Gson().fromJson(json, GroupBean.class);
        dataBeanList.addAll(groupBean.getData());
    }
}
