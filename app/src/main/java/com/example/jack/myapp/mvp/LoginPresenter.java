package com.example.jack.myapp.mvp;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.example.jack.myapp.AppConstant;
import com.example.jack.myapp.bean.ArticalBean;
import com.example.tulib.util.base.BasePresenter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lcy on 2018/4/8.
 */

public class LoginPresenter extends BasePresenter<LoginContract.Model,LoginContract.View> {
    public LoginPresenter(Context context, LoginContract.View mRootview) {
        super(new LoginModel(), mRootview,context);
    }
    @Override
    public void handleResponseError(Context context, Exception e) {
        super.handleResponseError(context, e);
    }
    /**
     * 登录
     */
    public void login(String username, String password) {
//        mModel.login(PreferencesUtil.getKeyHotelId(), PreferencesUtil.getKeyRestaurantId(),
//                DeviceUtils.getAndroidID(), PreferencesUtil.getBattery(), username, password)
//                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
//                .compose(XApi.<BaseObject>getApiTransformer())
//                .compose(XApi.<BaseObject>getScheduler())
//                .compose(bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
//                .subscribe(new ErrorHandleSubscriber<BaseObject>(mErrorHandler) {
//                    @Override
//                    public void onNext(BaseObject baseObject) {
//                        mRootView.hideLoading();
//                        if (baseObject.getResult() != null && !baseObject.getResult().isEmpty()) {
//                            if ("OK".equals(baseObject.getResult())) {
//                                update();
//                            }
//                        }
//                    }
//                });
    }
    public void getArticalDatas(){
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://www.wanandroid.com/")
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        ApiService apiService = retrofit.create(ApiService.class);
//        Observable<ArticalBean> observable = apiService.getArticals();
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ArticalBean>() {
//                    @Override
//                    public void onCompleted() {
//                        LogUtils.a("lcy","加载完成");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                            LogUtils.a("lcy",e.getMessage().toString());
//                    }
//
//                    @Override
//                    public void onNext(ArticalBean articalBean) {
//                        LogUtils.a("lcy",gson.toJson(articalBean).toString());
//                    }
//                });

        mMoudle.getArticalDatas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArticalBean>() {
                    @Override
                    public void onCompleted() {
                        LogUtils.a(AppConstant.TAG,"数据列表加载完成");
                    }
                    @Override
                    public void onError(Throwable e) {
                        LogUtils.a(AppConstant.TAG,e.getMessage().toString());
                    }
                    @Override
                    public void onNext(ArticalBean articalBean) {
                        if (null != articalBean) {
                            mRootview.showArticleDatas(articalBean);
                        }
                    }
                });

//        mMoudle.getArticalDatas()
//                .retryWhen(new RetryWithDelay(3,2))
//                .compose(XXApi.<ArticalBean>getApiTransformer())
//                .compose(XXApi.<ArticalBean>getScheduler())
//                .compose(bindToLifecycle(mRootview))
//                .subscribe(new ErrorHandleSubscriber<ArticalBean>(mErrorHandler) {
//                    @Override
//                    public void onNext(ArticalBean articalBean) {
//                        LogUtils.a("lcy",gson.toJson(articalBean).toString());
//                    }
//                });
    }
}