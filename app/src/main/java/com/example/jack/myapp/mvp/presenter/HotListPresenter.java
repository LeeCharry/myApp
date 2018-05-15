package com.example.jack.myapp.mvp.presenter;

import android.content.Context;

import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.BaseObject;
import com.example.jack.myapp.http.XXApi;
import com.example.jack.myapp.mvp.contract.HotListContract;

import com.example.jack.myapp.mvp.model.HotListModel;
import com.example.tulib.util.base.BasePresenter;

import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

/**
 * Created by lcy on 2018/4/8.
 */

public class HotListPresenter extends BasePresenter<HotListContract.Model,HotListContract.View> {
    public HotListPresenter(Context context, HotListContract.View mRootview) {
        super(new HotListModel(), mRootview,context);
    }
    @Override
    public void handleResponseError(Context context, Exception e) {
        super.handleResponseError(context, e);
    }
    public void queryByKey(int page,String key){
        mRootview.showLoading();
        mMoudle.queryByKey(page,key)
                .retryWhen(new RetryWithDelay(3,2))
                .compose(XXApi.<BaseObject<Artical>>getApiTransformer())
                .compose(XXApi.<BaseObject<Artical>>getScheduler())
                .compose(bindToLifecycle(mRootview))
                .subscribe(new ErrorHandleSubscriber<BaseObject<Artical>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseObject<Artical> articalBaseObject) {
                        mRootview.hideLoading();
                        if (articalBaseObject.getErrorCode() == 0){
                            if (null != articalBaseObject){
                                if (null != articalBaseObject.getData()) {
                                    mRootview.queryByKey(articalBaseObject.getData());
                                }
                            }
                        }else{
                            mRootview.showMessage(articalBaseObject.getErrorMsg().toString());
                        }
                    }
                });
    }
}