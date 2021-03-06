package com.example.jack.myapp.http;


import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.ArticalBean;
import com.example.jack.myapp.bean.BannerBean;
import com.example.jack.myapp.bean.BaseObject;
import com.example.jack.myapp.bean.HotBean;
import com.example.jack.myapp.bean.TreeBean;
import com.example.jack.myapp.bean.UserBean;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
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

    @GET("article/list/0/json/")
    Call<ResponseBody> test();

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @POST("user/login/")
    Observable<BaseObject<UserBean>> login(@Query("username")String username,
                                           @Query("password")String password);

    /**
     * 注册
     * @param username
     * @param password
     * @param repassword
     * @return
     */
    @POST("user/register/")
    Observable<BaseObject<UserBean>> register(@Query("username")String username,
                                     @Query("password")String password,
                                     @Query("repassword")String repassword);

    /**
     * 首页文章列表
     * @param page
     * @return
     */
    @GET("article/list/{page}/json/")
    Observable<BaseObject<Artical>> getArticalList(@Path("page") int page);

    /**
     * banner
     * @return
     */
    @GET("banner/json")
    Observable<BaseObject<List<BannerBean>>> getBanner();

    /**
     * 知识体系列表
     * @return
     */
    @GET("tree/json")
    Observable<BaseObject<List<TreeBean>>> getTree();

    /**
     * 搜索热词
     * @return
     */
    @GET("hotkey/json")
    Observable<BaseObject<List<HotBean>>> getHotKey();

    /**
     * 常用网站
     * @return
     */
    @GET("friend/json")
    Observable<BaseObject<List<HotBean>>> geFriends();

    /**
     * 搜索
     * @return
     */
    @POST("article/query/{page}/json")
    Observable<BaseObject<Artical>> queryByKey(@Path("page")int page, @Query("k")String key);

    /**
     * 搜索站内文章
     * http://www.wanandroid.com/lg/collect/1165/json
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseObject> collect(@Path("id")long id);

    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0/json?cid=60
     */
    @GET("article/list/0/json")
    Observable<BaseObject<Artical>> getArticalList(@Query("cid") long cid);

    /**
     * 收藏的文章
     * http://www.wanandroid.com/lg/collect/list/0/json
     */
    @GET("lg/collect/list/{page}/json")
    Observable<BaseObject<Artical>> getMarkedList(@Path("page") int page);

    /**
     * 删除收藏文章
     *http://www.wanandroid.com/lg/uncollect/2805/json
     */
    @POST("lg/uncollect/{id}/json")
    Observable<BaseObject> unCollectArtical(@Path("id") long id,@Query("originId") long originId);

    /**
     * 收藏
     * http://www.wanandroid.com/lg/collect/1165/json
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseObject> collectArtical(@Path("id") long id);
}
