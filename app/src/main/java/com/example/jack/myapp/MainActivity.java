package com.example.jack.myapp;

import android.content.Intent;
import com.blankj.utilcode.util.LogUtils;
import com.example.jack.myapp.bean.ArticalBean;
import com.example.jack.myapp.http.ApiService;
import com.example.jack.myapp.mvp.LoginContract;
import com.example.jack.myapp.mvp.LoginPresenter;
import com.example.tulib.util.base.XActivity;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends XActivity implements LoginContract.View {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
////        getArticalDatas();

//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new HttpLoggingInterceptor())
//                .addInterceptor(new HttpHeadInteceptor())
//                .build();
//        Retrofit retrofit = new Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl("http://www.wanandroid.com//hotkey/json/")
//                .build();
//        Observable<ArticalBean> observable = retrofit.create(ApiService.class).getArticals();
//        observable
//                .subscribeOn(Schedulers.io())
//                .doOnNext(new Action1<ArticalBean>() {
//            @Override
//            public void call(ArticalBean articalBean) {
//                LogUtils.a("lcy",Thread.currentThread().getName());
//                LogUtils.a("lcy",new Gson().toJson(articalBean).toString());
//            }
//        });
//    }

    @Override
    protected void initView() {
        getData();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    private void getData() {
        LoginPresenter loginPresenter = new LoginPresenter(MainActivity.this, MainActivity.this);
        loginPresenter.getArticalDatas();
    }

    /**
     * 获取列表数据
     */
    private void getArticalDatas() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
//        Call<ArticalBean> call = apiService.getArticals();
//        call.enqueue(new Callback<ArticalBean>() {
//            @Override
//            public void onResponse(Call<ArticalBean> call, retrofit2.Response<ArticalBean> response) {
//
//                ArticalBean articalBean = response.body();
//                ArticalBean.DataBean data = articalBean.getData();
//                LogUtils.a("lcy",Thread.currentThread().getName());
//                LogUtils.a("lcy",new Gson().toJson(articalBean).toString());
//            }
//            @Override
//            public void onFailure(Call<ArticalBean> call, Throwable t) {
//                LogUtils.a("lcy",t.getMessage().toString());
//            }
//        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMySelf() {

    }

    @Override
    public void showArticleDatas(ArticalBean articalBean) {
        LogUtils.a(AppConstant.TAG,"数据返回成功");
    }
}
