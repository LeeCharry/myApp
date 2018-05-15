package com.example.jack.myapp.mvp.presenter;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.BannerBean;
import com.example.jack.myapp.bean.BaseObject;
import com.example.jack.myapp.http.XXApi;
import com.example.jack.myapp.mvp.contract.ArticalListContract;
import com.example.jack.myapp.mvp.model.ArticalListModel;
import com.example.tulib.util.base.BasePresenter;

import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

/**
 * Created by lcy on 2018/4/8.
 */

public class ArticalListPresenter extends BasePresenter<ArticalListContract.Model,ArticalListContract.View> {
    public ArticalListPresenter(Context context, ArticalListContract.View mRootview) {
        super(new ArticalListModel(), mRootview,context);
    }
    @Override
    public void handleResponseError(Context context, Exception e) {
        super.handleResponseError(context, e);
    }

    /**
     * 获取知识体系下的文章
     * @param cid
     */
    public void getArticalist(long cid){
        mMoudle.getArticalist(cid)
                .retryWhen(new RetryWithDelay(3,2))
                .compose(XXApi.<BaseObject<Artical>>getApiTransformer())
                .compose(XXApi.<BaseObject<Artical>>getScheduler())
//                .compose(bindToLifecycle(mRootview))
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

}