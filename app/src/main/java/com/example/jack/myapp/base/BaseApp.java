package com.example.jack.myapp.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Application;
import android.app.ApplicationErrorReport;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.example.jack.myapp.BuildConfig;
import com.example.jack.myapp.http.Api;
import com.example.jack.myapp.http.XXApi;
import com.example.jack.myapp.widget.CrashHandler;
import com.example.tulib.util.http.NetError;
import com.example.tulib.util.http.NetProvider;
import com.example.tulib.util.http.RequestHandler;
import com.example.tulib.util.utils.DataHelper;

import java.io.File;

import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lcy on 2018/4/8.
 */

public class BaseApp extends Application {
    private static BaseApp instance;
    final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
//        initCcrash();
        //路由初始化
//        if (BuildConfig.DEBUG) {
//            ARouter.openDebug();
//            ARouter.openLog();
//        }
//        ARouter.init(this);
//            initCcrash();
        CrashHandler.getInstance().init(this);
//
//        x.Ext.init(this);
//        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
//};

        XXApi.registerProvider(new NetProvider() {
            @Override
            public String configBaseUrl() {
                return Api.getURL();
            }

            @Override
            public Interceptor[] configInterceptors() {
                return new Interceptor[0];
            }

            @Override
            public void configHttps(OkHttpClient.Builder builder) {

            }

            @Override
            public CookieJar configCookie() {
                return  null;
//                return new CookieJar() {
//                    @Override
//                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                        for (Cookie cookie : cookies) {
//                            Log.d("BaseApplication", "cookie " + cookie.toString());
//                            LogUtils.a("lcy", cookie.toString());
//                        }
//                        cookieStore.put(url.host(), cookies);
//                    }
//                    @Override
//                    public List<Cookie> loadForRequest(HttpUrl url) {
//                        LogUtils.a("lcy", url.toString());
//                        List<Cookie> cookies = cookieStore.get(url);
//                        return cookies != null ? cookies : new ArrayList<Cookie>();
//                    }
//                };
            }

            @Override
            public RequestHandler configHandler() {
//                return requestHandler;
                return null;
            }

            @Override
            public long configConnectTimeoutMills() {
                return 0;
            }

            @Override
            public long configReadTimeoutMills() {
                return 0;
            }

            @Override
            public boolean configLogEnable() {
                return true;
            }

            @Override
            public boolean handleError(NetError error) {
                return false;
            }

            @Override
            public File getFile() {
                return DataHelper.getCacheFile(BaseApp.this);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void initCcrash() {
        LogUtils.a("lcy", Environment.getExternalStorageDirectory().toString());
        LogUtils.a("lcy",getExternalCacheDir().toString());

        String crashPath = Environment.getExternalStorageDirectory() + File.separator + "crash.txt";
        File file = new File(crashPath);
        if (file.exists()) {
            file.delete();
//        }
        try {
            file.createNewFile();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
//            CrashUtils.init(file);
            CrashUtils.init();
//            LogUtils.a("lcy",init+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }

    private RequestHandler requestHandler = new RequestHandler() {
        @Override
        public Request onBeforeRequest(Request request, Interceptor.Chain chain) {
            return request;
        }
        @Override
        public Response onAfterRequest(Response response, String result, Interceptor.Chain chain) {
            int code = response.code();
            LogUtils.a("lcy",code);
            return null;
        }
    };

    public static BaseApp getAppInstance() {
        if (instance == null){
           instance = new BaseApp();
        }
        return instance;
    }
}
