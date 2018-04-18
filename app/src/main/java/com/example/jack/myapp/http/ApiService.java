package com.example.jack.myapp.http;


import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.ArticalBean;
import com.example.jack.myapp.bean.BannerBean;
import com.example.jack.myapp.bean.BaseObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lcy on 2018/4/8.
 */

public interface ApiService {
    //登录
//    @FormUrlEncoded
//    @POST("api/gingermenu/v1/{hotel_id}/{restaurant_id}/login")
//    Observable<BaseObject> login(@Path("hotel_id") String hotel_id,
//                                 @Path("restaurant_id") String restaurant_id,
//                                 @Query("device_id") String device_id,
//                                 @Query("device_battery") String device_battery,
//                                 @Field("username") String username,
//                                 @Field("password") String password);

    @GET("article/list/0/json/")
    Observable<ArticalBean> getArticals();


    @GET("article/list/{page}/json/")
    Observable<BaseObject<Artical>> getArticalList(@Path("page") int page);

    @GET("banner/json")
    Observable<BaseObject<List<BannerBean>>> getBanner();


}
