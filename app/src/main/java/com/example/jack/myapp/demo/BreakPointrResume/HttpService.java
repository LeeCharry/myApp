package com.example.jack.myapp.demo.BreakPointrResume;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lcy on 2018/6/20.
 */

public class HttpService extends Service {
    private Context context;
    private static final int UPDATE_PROGRESS = 101;
    private static final int DOWNLOAD_COMPLETE = 102;
    private static String APK_DIR = Environment.getExternalStorageDirectory() + "/Http/Apks/";
    private Intent broadcastIntent;
    public static String ACTION = "com.example.jack.myapp.demo.BreakPointrResume.MyBroadcastReceiver";
    private AppRvAdapter.MyBroadcastReceiver broadcastReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = HttpService.this;
//        EventTrans.getDefault().registerObserver(context);

        broadcastIntent = new Intent();
        broadcastIntent.setAction(ACTION);

        broadcastReceiver = new AppRvAdapter.MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(ACTION);
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String apkName = intent.getStringExtra("apk_name");
                String apkUrl = intent.getStringExtra("apk_url");
                try {
                    URL url = new URL(apkUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(30*1000);
                    connection.setConnectTimeout(30*1000);
                    connection.setUseCaches(false);
                    connection.connect();

                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream in = connection.getInputStream();
                        float contentLength = connection.getContentLength();

                        File outFile = null;
                        outFile = new File(APK_DIR);
                        if (!outFile.exists()) {
                            outFile.mkdirs();
                        }
                   String   APK_PATH = APK_DIR + apkName+".apk";
//
                        RandomAccessFile randomAccessFile = new RandomAccessFile(APK_PATH,"rw");
                        randomAccessFile.seek(0);

                        byte[] bytes = new byte[1024];
                        int len = 0;
                        float percentage = 0;
                        while ((len = in.read(bytes)) >= 0) {
                            randomAccessFile.write(bytes,0,len);
                            percentage+=(len/contentLength)*100;

                            Message msg = new Message();
                            if (percentage >= 100){
                                //下载完成
                                msg.what = DOWNLOAD_COMPLETE;
                                msg.arg1 = 100;
                            }else{
                                //正在下载
                                msg.what = UPDATE_PROGRESS;
                                msg.arg1 = (int) percentage;
                            }
                            Bundle bundle = new Bundle();
                            bundle.putString("apk_url",apkUrl);
                            msg.setData(bundle);
//                            EventTrans.getDefault().postMsg(msg);
//                            handler.sendMessage(msg);
                            broadcastIntent.putExtra("msg",msg);
                            context.sendBroadcast(broadcastIntent);
                        }
                        randomAccessFile.close();
                        in.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showShort(apkName+"下载出错");
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }
}
