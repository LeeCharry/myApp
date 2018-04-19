package com.example.jack.myapp.mvp.presenter;

import android.content.Context;

import com.example.jack.myapp.bean.BaseObject;
import com.example.jack.myapp.bean.HotBean;
import com.example.jack.myapp.http.XXApi;
import com.example.jack.myapp.mvp.contract.HotContract;
import com.example.jack.myapp.mvp.model.HotModel;
import com.example.tulib.util.base.BasePresenter;
import java.util.List;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
/**
 * Created by lcy on 2018/4/8.
 */

public class HotPresenter extends BasePresenter<HotContract.Model,HotContract.View> {
    public HotPresenter(Context context, HotContract.View mRootview) {
        super(new HotModel(), mRootview,context);
    }
    @Override
    public void handleResponseError(Context context, Exception e) {
        super.handleResponseError(context, e);
    }

    /**
     * 搜索热词
     */
    public void getHotKey(){
        mMoudle.getHotKey()
                .retryWhen(new RetryWithDelay(3,2))
                .compose(XXApi.<BaseObject<List<HotBean>>>getApiTransformer())
                .compose(XXApi.<BaseObject<List<HotBean>>>getScheduler())
                .compose(bindToLifecycle(mRootview))
                .subscribe(new ErrorHandleSubscriber<BaseObject<List<HotBean>>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseObject<List<HotBean>> baseObject) {
                        if (0 == baseObject.getErrorCode()){
                            if (null != baseObject.getData()) {
                                List<HotBean> data = baseObject.getData();
                                mRootview.getHotKey(data);
                            }
                        }else{
                            mRootview.showMessage(baseObject.getErrorMsg().toString());
                        }
                    }

                });
    }
    /**
     * 常用网站
     */
    public void getFriends(){
        mMoudle.getFriends()
                .retryWhen(new RetryWithDelay(3,2))
                .compose(XXApi.<BaseObject<List<HotBean>>>getApiTransformer())
                .compose(XXApi.<BaseObject<List<HotBean>>>getScheduler())
                .compose(bindToLifecycle(mRootview))
                .subscribe(new ErrorHandleSubscriber<BaseObject<List<HotBean>>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseObject<List<HotBean>> baseObject) {
                        if (0 == baseObject.getErrorCode()){
                            if (null != baseObject.getData()) {
                                List<HotBean> data = baseObject.getData();
                                mRootview.getFriends(data);
                            }
                        }else{
                            mRootview.showMessage(baseObject.getErrorMsg().toString());
                        }
                    }

                });
    }
}