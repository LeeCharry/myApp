package com.example.jack.myapp.demo.request;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by lcy on 2018/4/10.
 */
public class HttpHeadInteceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response;
    }
}
