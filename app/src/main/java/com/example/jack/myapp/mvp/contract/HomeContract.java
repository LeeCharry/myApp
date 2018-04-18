package com.example.jack.myapp.mvp.contract;

import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.ArticalBean;
import com.example.jack.myapp.bean.BannerBean;
import com.example.jack.myapp.bean.BaseObject;
import com.example.tulib.util.base.BaseView;

import java.util.List;

import rx.Observable;


/**
 * Created by lcy on 2018/4/8.
 */

public interface HomeContract {
     interface View extends BaseView{
        void getArticalist(Artical artical);
        void getBanners(List<BannerBean> bannerBeans);
    }
    interface Model{
        Observable<BaseObject<Artical>> getArticalist(int page);
        Observable<BaseObject<List<BannerBean>>> getBanners();
    }
}
