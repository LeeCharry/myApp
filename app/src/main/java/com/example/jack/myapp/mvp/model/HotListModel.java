package com.example.jack.myapp.mvp.model;

import com.example.jack.myapp.base.BaseModel;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.BaseObject;
import com.example.jack.myapp.mvp.contract.HotListContract;

import rx.Observable;

/**
 * Created by lcy on 2018/4/8.
 */

public class HotListModel extends BaseModel implements HotListContract.Model{

    @Override
    public Observable<BaseObject<Artical>> queryByKey(int page, String key) {
        return apiService.queryByKey(page,key);
    }
}
