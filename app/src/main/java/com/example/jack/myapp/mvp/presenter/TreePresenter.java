package com.example.jack.myapp.mvp.presenter;

import android.content.Context;

import com.example.jack.myapp.bean.BaseObject;
import com.example.jack.myapp.bean.TreeBean;

import com.example.jack.myapp.http.XXApi;
import com.example.jack.myapp.mvp.contract.TreeContract;
import com.example.jack.myapp.mvp.model.TreeModel;
import com.example.tulib.util.base.BasePresenter;


import java.util.List;

import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

/**
 * Created by lcy on 2018/4/8.
 */

public class TreePresenter extends BasePresenter<TreeContract.Model,TreeContract.View> {
    public TreePresenter(Context context, TreeContract.View mRootview) {
        super(new TreeModel(), mRootview,context);
    }
    @Override
    public void handleResponseError(Context context, Exception e) {
        super.handleResponseError(context, e);
    }
    public void getTree(){
        mMoudle.getTree()
                .retryWhen(new RetryWithDelay(3,2))
                .compose(XXApi.<BaseObject<List<TreeBean>>>getApiTransformer())
                .compose(XXApi.<BaseObject<List<TreeBean>>>getScheduler())
                .compose(bindToLifecycle(mRootview))
                .subscribe(new ErrorHandleSubscriber<BaseObject<List<TreeBean>>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseObject<List<TreeBean>> baseObject) {
                        if (0 == baseObject.getErrorCode()){
                            if (null != baseObject.getData()) {
                                List<TreeBean> data = baseObject.getData();
                                mRootview.getTree(data);
                            }
                        }else{
                            mRootview.showMessage(baseObject.getErrorMsg().toString());
                        }
                    }

                });
    }
}