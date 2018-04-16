package com.example.jack.myapp.base;

import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.example.jack.myapp.http.Api;
import com.example.jack.myapp.http.XXApi;
import com.example.tulib.util.http.NetError;
import com.example.tulib.util.http.NetProvider;
import com.example.tulib.util.http.RequestHandler;
import com.example.tulib.util.utils.DataHelper;

import java.io.File;
import java.util.ArrayList;
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

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);

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
//              return   new CookieJar(){
//
//                    @Override
//                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                        for (Cookie cookie : cookies) {
//                            Log.d("BaseApplication", "cookie " + cookie.toString());
//                        }
//                    }
//
//                    @Override
//                    public List<Cookie> loadForRequest(HttpUrl url) {
//                        return null;
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
