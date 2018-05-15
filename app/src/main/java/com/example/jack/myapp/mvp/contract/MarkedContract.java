package com.example.jack.myapp.mvp.contract;

import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.bean.BaseObject;
import com.example.tulib.util.base.BaseView;

import rx.Observable;


/**
 * Created by lcy on 2018/4/8.
 */

public interface MarkedContract {
     interface View extends BaseView{
        void getMarkedList(Artical artical);
        void unCollectArtical();
        void collectArtical();
    }
    interface Model{
        Observable<BaseObject<Artical>> getMarkedList(int page);
        Observable<BaseObject> unCollectArtical(long id,long originId);
        Observable<BaseObject> collectArtical(long id);
    }
}
