package com.example.jack.myapp.demo.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by lcy on 2018/9/20.
 */

public class LocalService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        bindKeepAliveService();
    }

    private void bindKeepAliveService() {
//        Intent intent = new Intent();
//        intent.setClassName()
//        bindService(service,conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
