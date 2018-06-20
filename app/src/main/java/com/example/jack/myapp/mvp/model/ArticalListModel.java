package com.example.jack.myapp.mvp.model;

import com.example.jack.myapp.base.BaseModel;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.BannerBean;
import com.example.jack.myapp.bean.BaseObject;
import com.example.jack.myapp.mvp.contract.ArticalListContract;
import com.example.jack.myapp.mvp.contract.HomeContract;

import java.util.List;

import rx.Observable;

/**
 * Created by lcy on 2018/4/8.
 */

public class ArticalListModel extends BaseModel implements ArticalListContract.Model {
    @Override
    public Observable<BaseObject<Artical>> getArticalist(long cid) {
        return apiService.getArticalList(cid);
    }
}
