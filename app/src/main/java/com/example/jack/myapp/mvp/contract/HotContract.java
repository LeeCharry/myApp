package com.example.jack.myapp.mvp.contract;

import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.BannerBean;
import com.example.jack.myapp.bean.BaseObject;
import com.example.jack.myapp.bean.HotBean;
import com.example.tulib.util.base.BaseView;

import java.util.List;

import rx.Observable;


/**
 * Created by lcy on 2018/4/8.
 */

public interface HotContract {
     interface View extends BaseView{
        void getHotKey(List<HotBean> hotBeanList);
        void getFriends(List<HotBean> hotBeanList);
    }
    interface Model{
        Observable<BaseObject<List<HotBean>>> getHotKey();
        Observable<BaseObject<List<HotBean>>> getFriends();
    }
}
