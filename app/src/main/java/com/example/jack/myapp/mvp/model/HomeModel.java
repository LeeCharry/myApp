package com.example.jack.myapp.mvp.model;

import com.example.jack.myapp.base.BaseModel;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.ArticalBean;
import com.example.jack.myapp.bean.BannerBean;
import com.example.jack.myapp.bean.BaseObject;
import com.example.jack.myapp.mvp.LoginContract;
import com.example.jack.myapp.mvp.contract.HomeContract;

import java.util.List;

import rx.Observable;

/**
 * Created by lcy on 2018/4/8.
 */

public class HomeModel extends BaseModel implements HomeContract.Model {


    @Override
    public Observable<BaseObject<Artical>> getArticalist(int page) {
        return apiService.getArticalList(page);
    }

    @Override
    public Observable<BaseObject<List<BannerBean>>> getBanners() {
        return apiService.getBanner();
    }
}
