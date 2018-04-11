package com.example.jack.myapp.demo.download;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.R;

import java.io.File;

/**
 * Created by lcy on 2018/4/11.
 */

public class DownLoadService extends Service {
    private String APK_URL = "https://raw.githubusercontent.com/wangzailfm/WanAndroidClient/master/app/release/app-release.apk";
    private Context context;
    private String apkPath;
    private DownloadHelper.OnDownloadListener onDownloadListener;
    private static final int NOTIFICATION_ID = 1000;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder notifyBuiler;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void setStop() {
        stopSelf();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;

        apkPath = Environment.getExternalStorageDirectory() + File.separator+ this.getPackageName()+ "/apk/wad.apk";
        File file = new File(apkPath);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setOnLoadListener();
        initNotifycation(context);
    }
    private void initNotifycation(Context context) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifyBuiler = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentText(  "%0")
                .setContentTitle("app下载")
                .setProgress(100, 0, false);
        notificationManager.notify(NOTIFICATION_ID,notifyBuiler.build());
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        DownloadHelper.download(APK_URL, apkPath, onDownloadListener);
        return super.onStartCommand(intent, flags, startId);
    }
    private void setOnLoadListener() {
        onDownloadListener = new DownloadHelper.OnDownloadListener() {
            @Override
            public void onStart() {
                ToastUtils.showShort("开始下载...");
            }
            @Override
            public void onSuccess(File file) {
                notificationManager.cancel(NOTIFICATION_ID);
                //安装apk
                LogUtils.a("lcy",file.getAbsolutePath().toString());
                stopSelf();
                notificationManager.cancel(NOTIFICATION_ID);
                installApk(file);

            }
            @Override
            public void onFail(File file, String failInfo) {
                notificationManager.cancel(NOTIFICATION_ID);
                ToastUtils.showShort(failInfo.toString());
            }
            @Override
            public void onProgress(int progress) {
                notifyBuiler.setProgress(100,progress,false)
                        .setContentText(progress+"%");
                notificationManager.notify(NOTIFICATION_ID,notifyBuiler.build());
            }
        };
    }
    /**
     * 安装APK
     * @param file
     */
    private void installApk(File file) {
        Uri uri = Uri.fromFile(file);
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        install.setDataAndType(uri, "application/vnd.android.package-archive");
        context.startActivity(install);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
