package com.example.jack.myapp.mvp.contract;

import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.BannerBean;
import com.example.jack.myapp.bean.BaseObject;
import com.example.jack.myapp.bean.TreeBean;
import com.example.tulib.util.base.BaseView;

import java.util.List;

import rx.Observable;


/**
 * Created by lcy on 2018/4/8.
 */

public interface TreeContract {
     interface View extends BaseView{
        void getTree(List<TreeBean> treeBean);
    }
    interface Model{
        Observable<BaseObject<List<TreeBean>>> getTree();
    }
}
