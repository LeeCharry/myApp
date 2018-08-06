package com.example.jack.myapp.demo.customize;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.R;

/**
 * 下载进度按钮
 */
public class MyDownloadButtonActivity extends AppCompatActivity {
    private int progress = 0;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (null != downLoadButton) {
                if (progress == 100) {
                    downLoadButton.setButtonState(MyDownloadButton.STATE_COMPLETE);
                    handler.removeCallbacks(this);
                } else {
                    progress += 5;
                    downLoadButton.setProgress(progress);
                    downLoadButton.setButtonState(MyDownloadButton.STATE_DOWNING);
                    handler.postDelayed(this, 1000);
                }
            }
        }
    };
    private MyDownloadButton downLoadButton;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloading_button);

        downLoadButton = findViewById(R.id.downLoadButton);

        downLoadButton.setOnMyDownloadButtonOnClickListener(new MyDownloadButton.OnMyDownloadButtonOnClickListener() {
            @Override
            public void onClick(View v, int buttonState) {
                switch (buttonState) {
                    case MyDownloadButton.STATE_NORMAL:
                        downLoadButton.setButtonState(MyDownloadButton.STATE_DOWNING);
                        //每个一秒加载5%
                        progress = 0;
                        handler.postDelayed(runnable,1000);
                        break;
                    case MyDownloadButton.STATE_COMPLETE:
//                        //下载完成
                        ToastUtils.showShort("等待安装中.....");
                        break;
                }
            }
        });
    }
}
