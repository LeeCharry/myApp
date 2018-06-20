package com.example.jack.myapp.demo.observer;

import com.blankj.utilcode.util.LogUtils;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by lcy on 2018/5/29.
 */

public class Student implements Observer {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
//        teacher = (Teacher) o;
        LogUtils.a("lcy",name+"今天的作业是"+arg.toString());
    }
}
