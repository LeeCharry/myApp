package com.example.jack.myapp.demo.head_line;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jack.myapp.R;
import com.example.jack.myapp.demo.head_line.helper.ItemDragHelperCallback;
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
    private List<ChannelBean> myChannelList = new ArrayList<>();
    private List<ChannelBean> recommendChannelList = new ArrayList<>();
    private BottomSheetDialog bottomDialog;
    private HeadlineAdapter headlineAdapter;
    private View ivCancel;
    private RecyclerView rvChannel;
    private UitlmateChannelAdapter uitlmateChannelAdapter;

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
//        headlineAdapter = new HeadlineAdapter(getSupportFragmentManager(), fragmentList);
//        viewpager.setAdapter(headlineAdapter);
//        indicator.setViewPager(viewpager);
        setlistener();
    }

    private void setlistener() {
        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet();
                //发送广播到另一个应用
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
            //将bottomSheetDialog设置全屏
//            View designBottomSheet = bottomDialog.getWindow().findViewById(android.support.design.R.id.design_bottom_sheet);
//            BottomSheetBehavior.from(designBottomSheet).setPeekHeight(getWindow().getDecorView().getHeight());
            bottomDialog.setCancelable(true);
        }
        bottomDialog.show();
    }

    private void initDialogView(View view) {
        tvEdit = view.findViewById(R.id.tv_edit);
        rvChannel = view.findViewById(R.id.rv);
        ivCancel = view.findViewById(R.id.iv_cancel);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        rvChannel.setLayoutManager(layoutManager);
        uitlmateChannelAdapter = new UitlmateChannelAdapter(myChannelList, recommendChannelList, this);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = uitlmateChannelAdapter.getItemViewType(position);
                return itemViewType == UitlmateChannelAdapter.MY_CHANNEL_TYPE || itemViewType == UitlmateChannelAdapter.OTHER_CHANNEL_TYPE ? 1 : 4;
            }
        });

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rvChannel);

        rvChannel.setAdapter(uitlmateChannelAdapter);
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
//        tvEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (tvEdit.getText().toString().equals("编辑")) {
//                    tvEdit.setText("完成");
//                    showDelete(true);
//                } else {
//                    tvEdit.setText("编辑");
//                    showDelete(false);
//                }
//            }
//        });
//        //删除mychannel
//        myChannelAdapter.setCallBack(new MyChannelAdapter2.CallBack() {
//            @Override
//            public void onDelete(View view,int position) {
//                float x = view.getX();
//                float y = view.getY();
//                int[] xy = new int[2];
//                view.getLocationInWindow(xy);
//
//                recommendChannelList.add(0, myChannelList.get(position).getTitle());
//                recommendChannelAdapter.notifyDataSetChanged();
//
//                fragmentList.remove(position);
////                try {
//                //报错 fagment is active,,所以注释，原因还不明，待究
////                    headlineAdapter.notifyDataSetChanged();
////                }catch (Exception e){
////                    LogUtils.a(AppConstant.TAG,e.getMessage().toString());
////                }
//                viewpager.setAdapter(headlineAdapter);
//                indicator.setViewPager(viewpager);
//
//                myChannelList.remove(position);
//                //设置显示删除移动动画标识
//                myChannelAdapter.deleteFlag = true;
//                myChannelAdapter.removeIndex = position;
//                myChannelAdapter.notifyDataSetChanged();
//
//            }
//        });
//
//        //点击
//        gvMyChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                headlineAdapter.notifyDataSetChanged();
//                indicator.setViewPager(viewpager);
//                indicator.setCurrentTab(position);
//                bottomDialog.dismiss();
//
//            }
//        });
//        //长按
//        gvMyChannel.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                if (!myChannelList.get(position).isToDelete()) {
//                    showDelete(true);
//                    tvEdit.setText("完成");
//                    //振动加缩放,出现删除按钮
//
//                } else {
//                    //只有振动加缩放
//                }
//                return true;
//            }
//        });
//
//        //添加
//        gvRecommendChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
////                String title = recommendChannelList.get(position);
////                int[] xy = new int[2];
////                view.getLocationInWindow(xy);
//
////                view.setDrawingCacheEnabled(true);
////                Bitmap drawingCache = view.getDrawingCache();
//
////                ImageView imageView = new ImageView(HeadLineHomeActivity.this);
////                imageView.setImageBitmap(drawingCache);
//
//                final ImageView moveImageView = getView(view);
//                if (moveImageView != null) {
//                    TextView newTextView = (TextView) view.findViewById(R.id.item_tv);
//                    final int[] startLocation = new int[2];
//                    newTextView.getLocationInWindow(startLocation);
////                    final String channel = recommendChannelList.get(position);
//                    myChannelAdapter.setVisible(false);
//                    //添加到最后一个
//                    myChannelAdapter.addItem(channel);
//                    new Handler().postDelayed(new Runnable() {
//                        public void run() {
//                            try {
//                                int[] endLocation = new int[2];
//                                //获取终点的坐标
//                                gvMyChannel.getChildAt(gvMyChannel.getLastVisiblePosition()).getLocationInWindow(endLocation);
//                                MoveAnim(moveImageView, startLocation, endLocation, channel, gvRecommendChannel, false);
//                                recommendChannelAdapter.setRemove(position);
//                            } catch (Exception localException) {
//                            }
//                        }
//                    }, 50L);
//                }
//
//                if (tvEdit.getText().toString().equals("完成")) {
//                    myChannelList.add(myChannelList.size(), new ChannelBean(title, true));
//                } else {
//                    myChannelList.add(myChannelList.size(), new ChannelBean(title, false));
//                }
//                //添加到我的频道的动画
//
////                myChannelAdapter.addFlag = true;
////                myChannelAdapter.tempX = xy[0];
////                myChannelAdapter.tempY = xy[1];
////                myChannelAdapter.tempBitmap = drawingCache;
//
//
//                myChannelAdapter.notifyDataSetChanged();
////                //adapter刷新完成后再设置为false
////                view.setDrawingCacheEnabled(false);
//
//                addFragment(title);
//
//                recommendChannelList.remove(position);
//                //设置显示删除移动动画标识
//                recommendChannelAdapter.deleteFlag = true;
//                recommendChannelAdapter.removeIndex = position;
//                recommendChannelAdapter.notifyDataSetChanged();
//            }
//        });
    }



    /**
     * 获取点击的Item的对应View，
     * 因为点击的Item已经有了自己归属的父容器MyGridView，所有我们要是有一个ImageView来代替Item移动
     *
     * @param view
     * @return
     */
    private ImageView getView(View view) {
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        Bitmap cache = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        ImageView iv = new ImageView(this);
        iv.setImageBitmap(cache);
        return iv;
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



    private void initData() {
        for (int i = 0; i < titles.length; i++) {
            HeadlineFragment fr = new HeadlineFragment();
            fr.setTitle(titles[i]);
            fragmentList.add(fr);
            myChannelList.add(new ChannelBean(titles[i], false));
        }


        recommendChannelList.add(new ChannelBean("精品课", false));
        recommendChannelList.add(new ChannelBean("历史", false));
        recommendChannelList.add(new ChannelBean("搞笑", false));
        recommendChannelList.add(new ChannelBean("音频", false));
        recommendChannelList.add(new ChannelBean("数码", false));
        recommendChannelList.add(new ChannelBean("美食", false));
        recommendChannelList.add(new ChannelBean("小视频", false));
        recommendChannelList.add(new ChannelBean("时尚", false));
        recommendChannelList.add(new ChannelBean("育儿", false));
        recommendChannelList.add(new ChannelBean("养生", false));
        recommendChannelList.add(new ChannelBean("电影", false));
        recommendChannelList.add(new ChannelBean("手机", false));
        recommendChannelList.add(new ChannelBean("宠物", false));
    }

    private void initView() {
        indicator = findViewById(R.id.indicator);
        viewpager = findViewById(R.id.viewpager);
        ivMore = findViewById(R.id.iv_more);

        rvChannel = findViewById(R.id.rv);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        rvChannel.setLayoutManager(layoutManager);
        uitlmateChannelAdapter = new UitlmateChannelAdapter(myChannelList, recommendChannelList, this);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = uitlmateChannelAdapter.getItemViewType(position);
                return itemViewType == UitlmateChannelAdapter.MY_CHANNEL_TYPE || itemViewType == UitlmateChannelAdapter.OTHER_CHANNEL_TYPE ? 1 : 4;
            }
        });

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rvChannel);

        rvChannel.setAdapter(uitlmateChannelAdapter);
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
