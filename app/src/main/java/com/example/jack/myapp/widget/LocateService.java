package com.example.jack.myapp.widget;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lcy on 2018/4/24.
 * 定位服务（网络位置）
 */

public class LocateService extends Service{
    private Context context;
    private LocationManager locationManager;
    private Location lastKnownLocation;
    private String  postUrl = "";
    private long delay;
    private long period;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        Bundle bundleExtra = intent.getBundleExtra("bundle");
//        postUrl =  bundleExtra.getString("post_url");
//        delay = bundleExtra.getLong("delay",delay);
//        period = bundleExtra.getLong("period",period);
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        ContentResolver contentResolver = getContentResolver();
        if (Settings.Secure.isLocationProviderEnabled(contentResolver, LocationManager.GPS_PROVIDER)) {
            getLocationData();
        }else{
            //定位权限设置
            startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @SuppressLint("MissingPermission")
    private void getLocationData() {
        List<String> providers = locationManager.getProviders(true);
        for (String provider: providers
             ) {
            Log.d("lcy",provider);
        }
        if (providers.contains(LocationManager.NETWORK_PROVIDER)){
             lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }else if (providers.contains(LocationManager.GPS_PROVIDER)){
            lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }else{
            Toast.makeText(context,"",Toast.LENGTH_SHORT).show();
            return;
        }
        LogUtils.a("lcy",lastKnownLocation.getLongitude()+" "+lastKnownLocation.getLatitude());

        //注册位置监听器
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        //(每隔一小时上传位置)
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        postLocation(lastKnownLocation);
//                        LogUtils.a("lcy","11111");
                    }
                }).start();
            }
        };
//        new Timer().schedule(timerTask,2000,60*60*1000);
        new Timer().schedule(timerTask,2000,1000);
    }

    private void postLocation(Location lastKnownLocation) {
        try {
            URL url = new URL(postUrl);
           HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
           httpURLConnection.setDoInput(true);
           httpURLConnection.setDoOutput(true);
           httpURLConnection.setUseCaches(false);
           httpURLConnection.setRequestMethod("POST");
           httpURLConnection.setConnectTimeout(30*1000);
           httpURLConnection.setReadTimeout(30*1000);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            LocationBean locationBean = new LocationBean(lastKnownLocation.getLongitude(), lastKnownLocation.getLatitude());
            String paramJson = new Gson().toJson(locationBean).toString();
            outputStream.write(paramJson.getBytes());
            outputStream.flush();
            outputStream.close();
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                Log.d("lcy", "postLocation: 位置上传成功" );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (null != location) {
                lastKnownLocation = location;
//                Log.d("lcy",location.getLongitude()+"  "+location.getLatitude());
                LogUtils.a("lcy",location.getLongitude()+" "+location.getLatitude());
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != locationListener) {
            locationManager.removeUpdates(locationListener);
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    class LocationBean{

        /**
         * longitude : 121.9876
         * Latitude : 31.2425
         */

        private double longitude;
        private double Latitude;

        public LocationBean(double longitude, double latitude) {
            this.longitude = longitude;
            Latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return Latitude;
        }

        public void setLatitude(double Latitude) {
            this.Latitude = Latitude;
        }
    }
}
