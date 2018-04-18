package com.example.jack.myapp.mvp.presenter;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.ArticalBean;
import com.example.jack.myapp.bean.BannerBean;
import com.example.jack.myapp.bean.BaseObject;
import com.example.jack.myapp.http.XXApi;
import com.example.jack.myapp.mvp.LoginContract;
import com.example.jack.myapp.mvp.LoginModel;
import com.example.jack.myapp.mvp.contract.HomeContract;
import com.example.jack.myapp.mvp.model.HomeModel;
import com.example.tulib.util.base.BasePresenter;

import java.util.List;

import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

/**
 * Created by lcy on 2018/4/8.
 */

public class HomePresenter extends BasePresenter<HomeContract.Model,HomeContract.View> {
    public HomePresenter(Context context, HomeContract.View mRootview) {
        super(new HomeModel(), mRootview,context);
    }
    @Override
    public void handleResponseError(Context context, Exception e) {
        super.handleResponseError(context, e);
    }
    public void getArticalist(int page){
        mMoudle.getArticalist(page)
                .retryWhen(new RetryWithDelay(3,2))
                .compose(XXApi.<BaseObject<Artical>>getApiTransformer())
                .compose(XXApi.<BaseObject<Artical>>getScheduler())
                .compose(bindToLifecycle(mRootview))
                .subscribe(new ErrorHandleSubscriber<BaseObject<Artical>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseObject<Artical> articalBaseObject) {
                        if (null != articalBaseObject){
                            if (null != articalBaseObject.getData()) {
                                mRootview.getArticalist(articalBaseObject.getData());
                            }
                        }
                    }

                });
    }

    public void getBanners(){
        mMoudle.getBanners()
                .retryWhen(new RetryWithDelay(3,2))
                .compose(XXApi.<BaseObject<List<BannerBean>>>getApiTransformer())
                .compose(XXApi.<BaseObject<List<BannerBean>>>getScheduler())
                .compose(bindToLifecycle(mRootview))
                .subscribe(new ErrorHandleSubscriber<BaseObject<List<BannerBean>>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseObject<List<BannerBean>> listBaseObject) {
                        if (null != listBaseObject){
                            if (null != listBaseObject.getData() ) {
                                mRootview.getBanners(listBaseObject.getData());
                            }
                        }
                    }

                });
    }
}