package com.example.jack.myapp.mvp.model;

import com.example.jack.myapp.base.BaseModel;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.BannerBean;
import com.example.jack.myapp.bean.BaseObject;
import com.example.jack.myapp.bean.TreeBean;
import com.example.jack.myapp.mvp.contract.HomeContract;
import com.example.jack.myapp.mvp.contract.TreeContract;

import java.util.List;

import rx.Observable;

/**
 * Created by lcy on 2018/4/8.
 */

public class TreeModel extends BaseModel implements TreeContract.Model {

    @Override
    public Observable<BaseObject<List<TreeBean>>> getTree() {
        return apiService.getTree();
    }
}
