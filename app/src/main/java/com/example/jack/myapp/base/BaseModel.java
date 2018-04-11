package com.example.jack.myapp.base;

import com.example.jack.myapp.http.Api;
import com.example.jack.myapp.http.ApiCache;
import com.example.jack.myapp.http.ApiService;

/**
 * Created by lcy on 2018/4/8.
 */

public class BaseModel {
    protected ApiService apiService;
    protected ApiCache apiCache;

    public BaseModel() {
        this.apiService = Api.getApiService();
        this.apiCache = Api.getApiCache();
    }
    public void onDestroy() {
        if (apiService != null) {
            apiService = null;
        }
        if (apiCache != null) {
            apiCache = null;
        }
    }
}
