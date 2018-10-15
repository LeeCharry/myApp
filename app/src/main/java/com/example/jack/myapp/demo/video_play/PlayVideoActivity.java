package com.example.jack.myapp.demo.video_play;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.jack.myapp.R;

import java.util.ArrayList;

import cn.jzvd.Jzvd;

/**
 * Created by lcy on 2018/9/7.
 */

public class PlayVideoActivity extends AppCompatActivity implements View.OnClickListener {
    private MyVideoPlayer jzvdStd;
    ArrayList<String> urlList = new ArrayList<>();
    private int index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        jzvdStd = findViewById(R.id.jzvdStd);

        findViewById(R.id.tiny_window).setOnClickListener(this);
        findViewById(R.id.auto_tiny_list_view).setOnClickListener(this);
        findViewById(R.id.auto_tiny_list_view_multi_holder).setOnClickListener(this);
        findViewById(R.id.auto_tiny_list_view_recycleview).setOnClickListener(this);
        findViewById(R.id.auto_tiny_list_view_recycleview_multiholder).setOnClickListener(this);


        //隐藏播放视频的全屏按钮
//        jzvdStd.fullscreenButton.setVisibility(View.GONE);
        urlList.add("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        urlList.add("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4");
        urlList.add("http://v.ysbang.cn//data/video/2015/rkb/2015rkb01.mp4");

        jzvdStd.setUrlList(urlList);
        //  http://v.ybang.cn//data/video/2015/rkb/2015rkb01.mp4
        //https://vjs.zencdn.net/v/oceans.mp4
//        jzvdStd.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4", "饺子闭眼睛",Jzvd.SCREEN_WINDOW_NORMAL);
        jzvdStd.setUp(urlList.get(index), "",Jzvd.SCREEN_WINDOW_NORMAL);
//        jzvdStd.setUp("https://vjs.zencdn.net/v/oceans.mp4", "",Jzvd.SCREEN_WINDOW_NORMAL);
//        jzvdStd.setUp("http://v.ysbang.cn//data/video/2015/rkb/2015rkb01.mp4", "",Jzvd.SCREEN_WINDOW_NORMAL);

        Glide.with(this)
                .load("https://cdn2.jianshu.io/assets/web/nav-logo-4c7bbafe27adc892f3046e6978459bac.png")
                .into(jzvdStd.thumbImageView);
        jzvdStd.startVideo();

    }


    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    /**
     * tiny_window).setOnClickListener(this);
     .auto_tiny_list_view).setOnClickListener(thi
     auto_tiny_list_view_multi_holder).setOnClick
     auto_tiny_list_view_recycleview).setOnClickL
     auto_tiny_list_view_recycleview_multiholder)
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiny_window:
                /**
                 * 小窗口播放
                 */
                jzvdStd.startWindowTiny();
                break;
            case R.id.auto_tiny_list_view:
                break;
            case R.id.auto_tiny_list_view_multi_holder:
                break;
            case R.id.auto_tiny_list_view_recycleview:
                break;
            case R.id.auto_tiny_list_view_recycleview_multiholder:
                break;
        }
    }
}
