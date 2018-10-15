package com.example.jack.myapp.demo.video_play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import cn.jzvd.JzvdStd;

/**
 * Created by lcy on 2018/9/7.
 * 隐藏jzvdStd 视频播放器下方的播放进度  重写该类
 */

public class MyVideoPlayer2 extends JzvdStd {

    public MyVideoPlayer2(Context context) {
        super(context);
    }

    public MyVideoPlayer2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAllControlsVisiblity(int topCon, int bottomCon, int startBtn, int loadingPro, int thumbImg, int bottomPro, int retryLayout) {
        super.setAllControlsVisiblity(topCon, bottomCon, startBtn, loadingPro, thumbImg, bottomPro, retryLayout);
        bottomProgressBar.setVisibility(GONE);
    }

    @Override
    public void dissmissControlView() {
//        super.dissmissControlView();
        if (currentState != CURRENT_STATE_NORMAL
                && currentState != CURRENT_STATE_ERROR
                && currentState != CURRENT_STATE_AUTO_COMPLETE) {
            post(new Runnable() {
                @Override
                public void run() {
                    bottomContainer.setVisibility(View.INVISIBLE);
                    topContainer.setVisibility(View.INVISIBLE);
                    startButton.setVisibility(View.INVISIBLE);
                    if (clarityPopWindow != null) {
                        clarityPopWindow.dismiss();
                    }
                    if (currentScreen != SCREEN_WINDOW_TINY) {
                        bottomProgressBar.setVisibility(View.GONE);
                    }
                }
            });
        }
    }
}
