package com.example.jack.myapp.mvp.model;

import com.example.jack.myapp.base.BaseModel;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.BannerBean;
import com.example.jack.myapp.bean.BaseObject;
import com.example.jack.myapp.bean.UserBean;
import com.example.jack.myapp.mvp.contract.HomeContract;
import com.example.jack.myapp.mvp.contract.LoginAndRegisContract;

import java.util.List;

import rx.Observable;

/**
 * Created by lcy on 2018/4/8.
 */

public class LoginAndRegisModel extends BaseModel implements LoginAndRegisContract.Model {
    @Override
    public Observable<BaseObject<UserBean>> register(String username, String password, String repassword) {
        return apiService.register(username,password,repassword);
    }

    @Override
    public Observable<BaseObject<UserBean>> login(String username, String password) {
        return apiService.login(username,password);
    }
}
