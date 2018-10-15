package com.example.jack.myapp.demo.video_play;

import android.content.Context;
import android.util.AttributeSet;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * Created by lcy on 2018/9/7.
 */

public class MyVideoPlayer extends JzvdStd {
    private ArrayList<String> urlList;
    private int index = 0;

    public void setUrlList(ArrayList<String> urlList) {
        this.urlList = urlList;
    }

    public MyVideoPlayer(Context context) {
        super(context);
    }

    public MyVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void onAutoCompletion() {
        super.onAutoCompletion();
        index = ++index % 3;
        setUp(urlList.get(index), "", Jzvd.SCREEN_WINDOW_NORMAL);
       startVideo();
    }

    @Override
    public void onCompletion() {
        super.onCompletion();
        LogUtils.a("lcy",""+index );
    }


}
