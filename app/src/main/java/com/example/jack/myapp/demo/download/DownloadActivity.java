package com.example.jack.myapp.demo.download;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.R;
import com.example.tulib.util.kit.Kits;

import java.io.File;
import java.io.IOException;

public class DownloadActivity extends AppCompatActivity {
    //  https://raw.githubusercontent.com/wangzailfm/WanAndroidClient/master/app/release/app-release.apk
    private DownloadHelper.OnDownloadListener onDownloadListener;
    private String APK_URL = "https://raw.githubusercontent.com/wangzailfm/WanAndroidClient/master/app/release/app-release.apk";
    private ProgressBar progressbar;
    private Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        initView();
        setOnLoadListener();
        final String apkPath = Environment.getExternalStorageDirectory() + File.separator+this.getPackageName()+ "/apk/wad.apk";
        File file = new File(apkPath);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadHelper.download(APK_URL, apkPath, onDownloadListener);
            }
        });

        //版本更新提示
        new UpdateManager(DownloadActivity.this)
                .showDialog();
    }
    private void initView() {
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        btnDownload = (Button) findViewById(R.id.btn_download);
        progressbar.setMax(100);
    }
    private void setOnLoadListener() {
        onDownloadListener = new DownloadHelper.OnDownloadListener() {
            @Override
            public void onStart() {
                btnDownload.setText("开始下载");
            }
            @Override
            public void onSuccess(File file) {
                ToastUtils.showShort("apk下载完成");
                btnDownload.setText("下载完成");
            }
            @Override
            public void onFail(File file, String failInfo) {
                btnDownload.setText("下载失败"+failInfo.toString());
            }
            @Override
            public void onProgress(int progress) {
                LogUtils.a("lcy",progress);
                btnDownload.setText("下载中");
                progressbar.setProgress(progress);
            }
        };
    }
}
