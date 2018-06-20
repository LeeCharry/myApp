package com.example.jack.myapp.demo.observer;

import com.blankj.utilcode.util.LogUtils;

import java.util.List;
import java.util.Observable;

/**
 * Created by lcy on 2018/5/29.
 */

public class Teacher extends Observable {
//    private List<Student> students;
    public Teacher() {
    }

    public void setHomeWork(String homeWork) {
        LogUtils.a("lcy","今天的作业是："+homeWork);

        setChanged();
        notifyObservers(homeWork);
    }
}
