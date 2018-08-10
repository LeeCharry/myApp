package com.example.jack.myapp.demo.head_line;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jack.myapp.R;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 2018/7/26.
 */

public class HeadLineHomeActivity extends AppCompatActivity {
    private SlidingTabLayout indicator;
    private ViewPager viewpager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] titles = {"关注", "推荐", "热点", "上海", "视频", "图片", "问答", "娱乐", "科技", "懂车帝", "财经", "军事", "体育", "国际"};
    private ImageView ivMore;
    private TextView tvEdit;
//    private RecyclerView rvMyChannel;
//    private RecyclerView rvRecommendChannel;
    private GridView gvMyChannel;
    private GridView gvRecommendChannel;
    private List<ChannelBean> myChannelList = new ArrayList<>();
    private List<String> recommendChannelList = new ArrayList<>();
    private MyChannelAdapter2 myChannelAdapter;
    private RecommendChannelAdapter2 recommendChannelAdapter;
    private BottomSheetDialog bottomDialog;
    private HeadlineAdapter headlineAdapter;
    private View ivCancel;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fragmentList.clear();
        fragmentList = null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headline);
        initView();
        initData();
        headlineAdapter = new HeadlineAdapter(getSupportFragmentManager(), fragmentList);
        viewpager.setAdapter(headlineAdapter);
        indicator.setViewPager(viewpager);
        setlistener();
    }

    private void setlistener() {
        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet();
            }
        });
    }

    private void showBottomSheet() {
        if (bottomDialog == null) {
            bottomDialog = new BottomSheetDialog(this, 0);
            bottomDialog.setCanceledOnTouchOutside(true);
            View view = LayoutInflater.from(this).inflate(R.layout.bottom_dialog_more, null);
            initDialogView(view);
            bottomDialog.setContentView(view);
            bottomDialog.setCancelable(true);
        }
            bottomDialog.show();
    }

    private void initDialogView(View view) {
        tvEdit = view.findViewById(R.id.tv_edit);
//        rvMyChannel = view.findViewById(R.id.rv_my_channel);
        gvMyChannel = view.findViewById(R.id.gv_my_channel);
//        rvRecommendChannel = view.findViewById(R.id.rv_recommend_channel);
        gvRecommendChannel = view.findViewById(R.id.gv_recommend_channel);
        ivCancel = view.findViewById(R.id.iv_cancel);
        myChannelAdapter = new MyChannelAdapter2(myChannelList);
//        rvMyChannel.addItemDecoration(new GridSpacingItemDecoration(4,30,false));
//        rvMyChannel.setLayoutManager(new GridLayoutManager(this, 4));
//        rvMyChannel.setItemAnimator(new DefaultItemAnimator());
//        rvMyChannel.setAdapter(myChannelAdapter);
        gvMyChannel.setAdapter(myChannelAdapter);

        recommendChannelAdapter = new RecommendChannelAdapter2(recommendChannelList);
//        rvRecommendChannel.addItemDecoration(new GridSpacingItemDecoration(4,30,false));
//        rvRecommendChannel.setLayoutManager(new GridLayoutManager(this, 4));
//        rvRecommendChannel.setItemAnimator(new DefaultItemAnimator());
//        rvRecommendChannel.setAdapter(recommendChannelAdapter);
        gvRecommendChannel.setAdapter(recommendChannelAdapter);

        initClickListener();
    }

    private void initClickListener() {
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
            }
        });
        //编辑
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvEdit.getText().toString().equals("编辑")) {
                    tvEdit.setText("完成");
                    showDelete(true);
                } else {
                    tvEdit.setText("编辑");
                    showDelete(false);
                }
            }
        });
        //删除mychannel
        myChannelAdapter.setCallBack(new MyChannelAdapter2.CallBack() {
            @Override
            public void onDelete(int position) {

                recommendChannelList.add(0, myChannelList.get(position).getTitle());
                recommendChannelAdapter.notifyDataSetChanged();

                fragmentList.remove(position);
                headlineAdapter.notifyDataSetChanged();
                viewpager.setAdapter(headlineAdapter);
                indicator.setViewPager(viewpager);


                myChannelList.remove(position);
                myChannelAdapter.notifyDataSetChanged();




            }
        });

        //点击
        gvMyChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                headlineAdapter.notifyDataSetChanged();
                indicator.setViewPager(viewpager);
                indicator.setCurrentTab(position);
                bottomDialog.dismiss();

            }
        });
        //长按
        gvMyChannel.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (!myChannelList.get(position).isToDelete()) {
                    showDelete(true);
                    tvEdit.setText("完成");
                    //振动加缩放,出现删除按钮

                } else {
                    //只有振动加缩放
                }
                return true;
            }

        });

        //添加
        gvRecommendChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = recommendChannelList.get(position);
                if (tvEdit.getText().toString().equals("完成")) {
                    myChannelList.add(myChannelList.size(), new ChannelBean(title, true));
                } else {
                    myChannelList.add(myChannelList.size(), new ChannelBean(title, false));
                }
                myChannelAdapter.notifyDataSetChanged();

                addFragment(title);

                recommendChannelList.remove(position);
                recommendChannelAdapter.notifyDataSetChanged();

            }
        });
    }

    /**
     * 添加fragment
     *
     * @param title
     */
    private void addFragment(String title) {
        HeadlineFragment headlineFragment = new HeadlineFragment();
        headlineFragment.setTitle(title);
        fragmentList.add(headlineFragment);
        headlineAdapter.notifyDataSetChanged();
        viewpager.setAdapter(headlineAdapter);
        indicator.setViewPager(viewpager);
    }

    /**
     * 是否显示删除按钮
     *
     * @param isTodelete
     */
    private void showDelete(boolean isTodelete) {
        for (ChannelBean bean : myChannelList
                ) {
            bean.setToDelete(isTodelete);
        }
        myChannelAdapter.notifyDataSetChanged();
    }

    private void initData() {
        for (int i = 0; i < titles.length; i++) {
            HeadlineFragment fr = new HeadlineFragment();
            fr.setTitle(titles[i]);
            fragmentList.add(fr);
            myChannelList.add(new ChannelBean(titles[i], false));
        }


        recommendChannelList.add("精品课");
        recommendChannelList.add("历史");
        recommendChannelList.add("搞笑");
        recommendChannelList.add("音频");
        recommendChannelList.add("数码");
        recommendChannelList.add("美食");
        recommendChannelList.add("小视屏");
        recommendChannelList.add("时尚");
        recommendChannelList.add("育儿");
        recommendChannelList.add("养生");
        recommendChannelList.add("电影");
        recommendChannelList.add("手机");
        recommendChannelList.add("宠物");
    }

    private void initView() {
        indicator = findViewById(R.id.indicator);
        viewpager = findViewById(R.id.viewpager);
        ivMore = findViewById(R.id.iv_more);
    }

    private void setTabPagerIndicator() {
//        indicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_NOWEIGHT_NOEXPAND_SAME);// 设置模式，一定要先设置模式
//        indicator.setDividerColor(Color.parseColor("#00bbcf"));// 设置分割线的颜色
//        indicator.setDividerPadding(Util.dip2px(this, 10));
//        indicator.setIndicatorColor(Color.parseColor("#43A44b"));// 设置底部导航线的颜色
//        indicator.setTextColorSelected(Color.parseColor("#43A44b"));// 设置tab标题选中的颜色
//        indicator.setTextColor(Color.parseColor("#797979"));// 设置tab标题未被选中的颜色
//        indicator.setTextSize(Util.sp2px(this, 16));// 设置字体大小
    }
}
