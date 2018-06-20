package com.example.jack.myapp.bean;

import java.io.Serializable;

/**
 * Created by lcy on 2018/6/7.
 */

public class ChildBean implements Serializable {
    private String item0;
    private String item1;
    private String item2;

    public ChildBean(String item0, String item1, String item2) {
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
    }

    public String getItem0() {
        return item0;
    }

    public void setItem0(String item0) {
        this.item0 = item0;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }
}
