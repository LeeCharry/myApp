package com.example.jack.myapp.mvp.contract;

import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.BannerBean;
import com.example.jack.myapp.bean.BaseObject;
import com.example.jack.myapp.bean.UserBean;
import com.example.tulib.util.base.BaseView;

import java.util.List;

import rx.Observable;


/**
 * Created by lcy on 2018/4/8.
 */

public interface LoginAndRegisContract {
     interface View extends BaseView{
        void register(UserBean userBean);
        void login(UserBean userBean);
    }
    interface Model{
        Observable<BaseObject<UserBean>> register(String username,String password,String repassword);
        Observable<BaseObject<UserBean>> login(String username,String password);
    }
}
