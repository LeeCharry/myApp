package com.example.jack.myapp.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.jack.myapp.R;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.BaseObject;
import com.example.jack.myapp.http.XXApi;
import com.example.jack.myapp.mvp.contract.ArticalListContract;
import com.example.jack.myapp.mvp.contract.MarkedContract;
import com.example.jack.myapp.mvp.model.ArticalListModel;
import com.example.jack.myapp.mvp.model.MarkedModel;
import com.example.jack.myapp.wanandroid.activity.LoginActivity;
import com.example.tulib.util.base.BasePresenter;

import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

/**
 * Created by lcy on 2018/4/8.
 */

public class MarkedPresenter extends BasePresenter<MarkedContract.Model,MarkedContract.View> {
    public MarkedPresenter(Context context, MarkedContract.View mRootview) {
        super(new MarkedModel(), mRootview,context);
    }
    @Override
    public void handleResponseError(Context context, Exception e) {
        super.handleResponseError(context, e);
    }

    /**
     * 我收藏的
     * @param page
     */
    public void getMarkedList(int page){
        mMoudle.getMarkedList(page)
                .retryWhen(new RetryWithDelay(3,2))
                .compose(XXApi.<BaseObject<Artical>>getApiTransformer())
                .compose(XXApi.<BaseObject<Artical>>getScheduler())
                .compose(bindToLifecycle(mRootview))
                .subscribe(new ErrorHandleSubscriber<BaseObject<Artical>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseObject<Artical> articalBaseObject) {
                        if (null != articalBaseObject){
                            if (articalBaseObject.getErrorCode() == -1){
                               String errorMsg = articalBaseObject.getErrorMsg().toString();
                                mRootview.showMessage(errorMsg);
                                if (errorMsg.contains(context.getString(R.string.login_first))) {
                                    context.startActivity(new Intent(context, LoginActivity.class));
                                }
                            }else if (articalBaseObject.getErrorCode() == 0){
                                mRootview.getMarkedList(articalBaseObject.getData());
                            }
                        }
                    }
                });
    }
    /**
     * 收藏
     * @param id
     * @param id
     */
    public void collectArtical(long id){
        mMoudle.collectArtical(id)
                .retryWhen(new RetryWithDelay(3,2))
                .compose(XXApi.<BaseObject>getApiTransformer())
                .compose(XXApi.<BaseObject>getScheduler())
//                .compose(bindToLifecycle(mRootview))
                .subscribe(new ErrorHandleSubscriber<BaseObject>(mErrorHandler) {
                    @Override
                    public void onNext(BaseObject articalBaseObject) {
                        if (null != articalBaseObject){
                            if (articalBaseObject.getErrorCode() == -1){
                                String errorMsg = articalBaseObject.getErrorMsg().toString();
                                mRootview.showMessage(errorMsg);
                                if (errorMsg.contains(context.getString(R.string.login_first))) {
                                    context.startActivity(new Intent(context, LoginActivity.class));
                                }
                            }else if (articalBaseObject.getErrorCode() == 0){
                                mRootview.collectArtical();
                            }
                        }
                    }
                });
    }
    /**
     * 取消收藏
     * @param id
     * @param originId
     */
    public void unCollectArtical(long id,long originId){
        mMoudle.unCollectArtical(id,originId)
                .retryWhen(new RetryWithDelay(3,2))
                .compose(XXApi.<BaseObject>getApiTransformer())
                .compose(XXApi.<BaseObject>getScheduler())
//                .compose(bindToLifecycle(mRootview))
                .subscribe(new ErrorHandleSubscriber<BaseObject>(mErrorHandler) {
                    @Override
                    public void onNext(BaseObject articalBaseObject) {
                        if (null != articalBaseObject){
                            if (articalBaseObject.getErrorCode() == -1){
                                mRootview.showMessage(articalBaseObject.getErrorMsg().toString());
                            }else if (articalBaseObject.getErrorCode() == 0){
                                mRootview.unCollectArtical();
                            }
                        }
                    }
                });
    }
}