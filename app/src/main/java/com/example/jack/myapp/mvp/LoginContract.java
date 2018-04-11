package com.example.jack.myapp.mvp;

import com.example.jack.myapp.bean.ArticalBean;
import com.example.tulib.util.base.BaseView;

import rx.Observable;


/**
 * Created by lcy on 2018/4/8.
 */

public interface LoginContract {
     interface View extends BaseView{
        void showArticleDatas(ArticalBean articalBean);
    }
    interface Model{
        Observable<ArticalBean> getArticalDatas();
    }
}
