package com.example.jack.myapp.mvp.model;

import com.example.jack.myapp.base.BaseModel;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.BaseObject;
import com.example.jack.myapp.mvp.contract.ArticalListContract;
import com.example.jack.myapp.mvp.contract.MarkedContract;

import rx.Observable;

/**
 * Created by lcy on 2018/4/8.
 */

public class MarkedModel extends BaseModel implements MarkedContract.Model {

    @Override
    public Observable<BaseObject<Artical>> getMarkedList(int page) {
        return apiService.getMarkedList(page);
    }
    @Override
    public Observable<BaseObject> unCollectArtical(long id, long originId) {
        return apiService.unCollectArtical(id,originId);
    }
    @Override
    public Observable<BaseObject> collectArtical(long id) {
        return apiService.collectArtical(id);
    }
}
