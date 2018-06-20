package com.example.jack.myapp.demo.expandlistview;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jack.myapp.R;
import com.example.jack.myapp.bean.ChildBean;
import com.example.tulib.util.utils.DeviceUtil;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by lcy on 2018/5/10.
 */

public class ExpandAdapter2 extends BaseExpandableListAdapter {
    private Context context;
    private List<GroupBean.DataBean> dataBeanList;

    ArrayList<ChildBean> childBeans = new ArrayList<>();
    private int total = 0;

    public ExpandAdapter2(List<GroupBean.DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
    }

    @Override
    public int getGroupCount() {
        return dataBeanList == null ? 0 : dataBeanList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        switch (groupPosition) {
            case 0:
                return 3;
            default:
                return 2;
        }
//        return dataBeanList.get(groupPosition).getChildData() == null ? 0 : dataBeanList.get(groupPosition).getChildCount();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return "123456";
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return getCombinedChildId(groupPosition, childPosition);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        context = parent.getContext();
        ParentHolder parentHolder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_parent, null);
            parentHolder = new ParentHolder(convertView);
            convertView.setTag(parentHolder);
        } else {
            parentHolder = (ParentHolder) convertView.getTag();
        }
        if (groupPosition == 0) {
            parentHolder.tv1.setText("中西医结合医院");
            parentHolder.tv2.setText("2017-12-10");
            parentHolder.tv3.setText("门急诊");
        } else {
            parentHolder.tv1.setText("上海交通大学附属医院");
            parentHolder.tv2.setText("2017-02-13");
            parentHolder.tv3.setText("普通门诊");
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_child, null);
            childHolder = new ChildHolder(convertView);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        if (childPosition == 0) {
            childHolder.tv1.setText("血常规五分类");
        } else {
            childHolder.tv1.setText("血液报告");
        }
        childHolder.setData(groupPosition, childPosition);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ParentHolder {
        ImageView iv1;
        TextView tv1;
        TextView tv2;
        TextView tv3;

        public ParentHolder(View view) {
            iv1 = view.findViewById(R.id.iv1);
            tv1 = view.findViewById(R.id.tv1);
            tv2 = view.findViewById(R.id.tv2);
            tv3 = view.findViewById(R.id.tv3);
        }
    }

    class ChildHolder {
        TextView tv1;
        LinearLayout llChild;
        TextView tvChild;

        public ChildHolder(View view) {
            tv1 = view.findViewById(R.id.tv1);
            llChild = view.findViewById(R.id.ll_child);
        }

        public void setData(int groupPosition, int childPosition) {
            childBeans.clear();
            if (groupPosition == 0) {
                if (childPosition == 0) {
                    childBeans.add(new ChildBean("嗜碱性粒细胞计数", "5", "1-4"));
                    childBeans.add(new ChildBean("细胞淋巴百分率", "5", "6-9"));
                    childBeans.add(new ChildBean("平均血红蛋白含量", "5", "1-10"));
                } else if (childPosition == 1) {
                    childBeans.add(new ChildBean("嗜碱性粒细胞计数", "5", "1-4"));
                    childBeans.add(new ChildBean("嗜碱性粒细胞计数", "5", "1-4"));
                    childBeans.add(new ChildBean("细胞淋巴百分率", "5", "6-9"));
                    childBeans.add(new ChildBean("平均血红蛋白含量", "5", "1-10"));
                } else {
                    childBeans.add(new ChildBean("细胞淋巴百分率", "5", "6-9"));
                    childBeans.add(new ChildBean("平均血红蛋白含量", "5", "1-10"));
                }

            } else {
                if (childPosition == 0) {
                    childBeans.add(new ChildBean("嗜碱性粒细胞计数", "5", "1-4"));

                } else if (childPosition == 1) {
                    childBeans.add(new ChildBean("细胞淋巴百分率", "5", "6-9"));
                    childBeans.add(new ChildBean("平均血红蛋白含量", "5", "1-10"));
                } else {
                    childBeans.add(new ChildBean("细胞淋巴百分率", "5", "6-9"));
                    childBeans.add(new ChildBean("平均血红蛋白含量", "5", "1-10"));
                }
            }
            addChildItem(llChild, childBeans);
        }
    }

    /**
     * 根据子条目个数添加子条目
     *
     * @param llChild
     * @param childBeans
     */
    private void addChildItem(LinearLayout llChild, ArrayList<ChildBean> childBeans) {
      llChild.removeAllViews();
        for (int i = 0; i < childBeans.size(); i++) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_child2, null);
            initContentView(inflate, childBeans.get(i));
            llChild.addView(inflate,i);
            total++;
        }
    }
    private void initContentView(View inflate, ChildBean childerBean) {
        TextView item0 = inflate.findViewById(R.id.item0);
        TextView item1 = inflate.findViewById(R.id.item1);
        TextView item2 = inflate.findViewById(R.id.item2);
        item0.setText(childerBean.getItem0());
        item1.setText(childerBean.getItem1());
        item2.setText(childerBean.getItem2());
    }
}
