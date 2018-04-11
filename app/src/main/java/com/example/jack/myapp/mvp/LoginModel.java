package com.example.jack.myapp.mvp;

import com.example.jack.myapp.base.BaseModel;
import com.example.jack.myapp.bean.ArticalBean;
import com.example.tulib.util.base.BaseView;

import rx.Observable;

/**
 * Created by lcy on 2018/4/8.
 */

public class LoginModel extends BaseModel implements LoginContract.Model {

    @Override
    public Observable<ArticalBean> getArticalDatas() {
        return apiService.getArticals();
    }
}
