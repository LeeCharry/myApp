package com.example.jack.myapp.demo.expandlistview;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lcy on 2018/5/10.
 */

public class GroupBean implements Serializable{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * groupId : 1
         * groupName : group1
         * childCount : 5
         * childData : ["child0","child1","child2","child3","child4"]
         */

        private int groupId;
        private String groupName;
        private int childCount;
        private List<String> childData;

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public int getChildCount() {
            return childCount;
        }

        public void setChildCount(int childCount) {
            this.childCount = childCount;
        }

        public List<String> getChildData() {
            return childData;
        }

        public void setChildData(List<String> childData) {
            this.childData = childData;
        }
    }
}
