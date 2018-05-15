package com.example.jack.myapp.mvp.contract;

import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.BaseObject;
import com.example.tulib.util.base.BaseView;

import rx.Observable;


/**
 * Created by lcy on 2018/4/8.
 */

public interface HotListContract {
     interface View extends BaseView{
        void queryByKey(Artical hotlistBean);
    }
    interface Model{
        Observable<BaseObject<Artical>> queryByKey(int page, String key);
    }
}
