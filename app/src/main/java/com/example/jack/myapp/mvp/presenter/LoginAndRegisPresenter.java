package com.example.jack.myapp.mvp.presenter;

import android.content.Context;

import com.example.jack.myapp.UserManager;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.BannerBean;
import com.example.jack.myapp.bean.BaseObject;
import com.example.jack.myapp.bean.UserBean;
import com.example.jack.myapp.http.XXApi;
import com.example.jack.myapp.mvp.contract.LoginAndRegisContract;
import com.example.jack.myapp.mvp.model.HomeModel;
import com.example.jack.myapp.mvp.model.LoginAndRegisModel;
import com.example.tulib.util.base.BasePresenter;

import java.util.List;

import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

/**
 * Created by lcy on 2018/4/8.
 */

public class LoginAndRegisPresenter extends BasePresenter<LoginAndRegisContract.Model,LoginAndRegisContract.View> {
    public LoginAndRegisPresenter(Context context, LoginAndRegisContract.View mRootview) {
        super(new LoginAndRegisModel(), mRootview,context);
    }
    @Override
    public void handleResponseError(Context context, Exception e) {
        super.handleResponseError(context, e);
    }
    /**
     * 注册
     * @param username
     * @param password
     * @param repassword
     */
    public void register(String username,String password,String repassword){
        mMoudle.register(username,password,repassword)
                .retryWhen(new RetryWithDelay(3,2))
                .compose(XXApi.<BaseObject<UserBean>>getApiTransformer())
                .compose(XXApi.<BaseObject<UserBean>>getScheduler())
                .compose(bindToLifecycle(mRootview))
                .subscribe(new ErrorHandleSubscriber<BaseObject<UserBean>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseObject<UserBean> userBeanBaseObject) {
                        if (0 == userBeanBaseObject.getErrorCode()){
                            if (null != userBeanBaseObject.getData()) {
                                UserBean data = userBeanBaseObject.getData();
                                UserManager.saveUserBean(data);
//                                UserManager.setLogin(true);
                                mRootview.register(data);
                            }
                        }else{
                            mRootview.showMessage(userBeanBaseObject.getErrorMsg().toString());
                        }
                    }
                });
    }

    /**
     * 登录
     * @param username
     * @param password
     */
    public void login(String username,String password){
        mRootview.showLoading();
        mMoudle.login(username,password)
                .retryWhen(new RetryWithDelay(3,2))
                .compose(XXApi.<BaseObject<UserBean>>getApiTransformer())
                .compose(XXApi.<BaseObject<UserBean>>getScheduler())
                .compose(bindToLifecycle(mRootview))
                .subscribe(new ErrorHandleSubscriber<BaseObject<UserBean>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseObject<UserBean> userBeanBaseObject) {
                        mRootview.hideLoading();
                        if (0 == userBeanBaseObject.getErrorCode()){
                            if (null != userBeanBaseObject.getData()) {
                                UserBean data = userBeanBaseObject.getData();
                                UserManager.saveUserBean(data);
                                UserManager.setLogin(true);
                                mRootview.login(data);
                            }
                        }else{
                            mRootview.showMessage(userBeanBaseObject.getErrorMsg().toString());
                        }
                    }
                });
    }

}