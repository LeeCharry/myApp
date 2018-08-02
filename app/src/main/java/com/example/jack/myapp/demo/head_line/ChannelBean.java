package com.example.jack.myapp.demo.head_line;

import java.io.Serializable;

/**
 * Created by lcy on 2018/7/27.
 */

public class ChannelBean implements Serializable{
    private String title;
    private boolean isToDelete;

    public ChannelBean(String title, boolean isToDelete) {
        this.title = title;
        this.isToDelete = isToDelete;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isToDelete() {
        return isToDelete;
    }

    public void setToDelete(boolean toDelete) {
        isToDelete = toDelete;
    }
}
