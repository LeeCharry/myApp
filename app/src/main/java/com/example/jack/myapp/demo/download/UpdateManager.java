package com.example.jack.myapp.demo.download;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.R;

import java.io.File;

/**
 * Created by lcy on 2018/4/11.
 */

public class UpdateManager {
    private static final int NOTIFICATION_ID = 1000;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder notifyBuiler;
    private  int progress;
    private DownloadHelper.OnDownloadListener onDownloadListener;
    private String APK_URL = "https://raw.githubusercontent.com/wangzailfm/WanAndroidClient/master/app/release/app-release.apk";
    private Context context;
    private String apkPath;

    public UpdateManager(Context context) {
        this.context = context;
        apkPath = Environment.getExternalStorageDirectory() + File.separator+context.getPackageName()+ "/apk/wad.apk";
        setOnLoadListener();
        initNotifycation(context);

    }

    private void initNotifycation(Context context) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifyBuiler = new NotificationCompat.Builder(context)
               .setSmallIcon(R.mipmap.ic_launcher_round)
               .setContentText(  "%0")
                .setContentTitle("app下载")
               .setProgress(100, progress, false);
        notificationManager.notify(NOTIFICATION_ID,notifyBuiler.build());
    }

    public void showDialog() {
       new AlertDialog.Builder(context)
                .setTitle("更新提示")
                .setMessage("更新内容\n1、个人中心添加。。。\n2、首页banner图片。。\n3、详情页面。。。\n")
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        downLoadApk();
                    }
                }).setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).setCancelable(false)
                .show();
    }
    private void downLoadApk() {
        File file = new File(apkPath);
        if (file.exists()){
            file.delete();
        }
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(context, DownLoadService.class);
        context.startService(intent);
//        DownloadHelper.download(APK_URL, apkPath, onDownloadListener);
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
}
