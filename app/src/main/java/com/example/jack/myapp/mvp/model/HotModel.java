package com.example.jack.myapp.mvp.model;

import com.example.jack.myapp.base.BaseModel;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.BannerBean;
import com.example.jack.myapp.bean.BaseObject;
import com.example.jack.myapp.bean.HotBean;
import com.example.jack.myapp.mvp.contract.HomeContract;
import com.example.jack.myapp.mvp.contract.HotContract;

import java.util.List;

import rx.Observable;

/**
 * Created by lcy on 2018/4/8.
 */

public class HotModel extends BaseModel implements HotContract.Model {

    @Override
    public Observable<BaseObject<List<HotBean>>> getHotKey() {
        return apiService.getHotKey();
    }
    @Override
    public Observable<BaseObject<List<HotBean>>> getFriends() {
        return apiService.geFriends();
    }
}
