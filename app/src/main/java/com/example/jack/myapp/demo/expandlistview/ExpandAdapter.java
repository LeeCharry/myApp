package com.example.jack.myapp.demo.expandlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.jack.myapp.R;

import java.util.List;

/**
 * Created by lcy on 2018/5/10.
 */

public class ExpandAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<GroupBean.DataBean> dataBeanList;

    public ExpandAdapter(List<GroupBean.DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
    }

    @Override
    public int getGroupCount() {
        return dataBeanList == null ? 0 : dataBeanList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dataBeanList.get(groupPosition).getChildData() == null ? 0 : dataBeanList.get(groupPosition).getChildCount();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dataBeanList.get(groupPosition).getChildData().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return getCombinedChildId(groupPosition,childPosition);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group, null);
            parentHolder = new ParentHolder(convertView);
            convertView.setTag(parentHolder);
        }else{
            parentHolder = (ParentHolder) convertView.getTag();
        }
        parentHolder.tvParent.setText(dataBeanList.get(groupPosition).getGroupName().toString());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group_child,null);
            childHolder = new ChildHolder(convertView);
            convertView.setTag(childHolder);
        }else{
            childHolder = (ChildHolder) convertView.getTag();
        }
        childHolder.tvChild.setText(dataBeanList.get(groupPosition).getChildData().get(childPosition).toString());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ParentHolder{
         TextView tvParent;
        public ParentHolder(View view) {
            tvParent = view.findViewById(R.id.tv_parent);
        }
    }
    class ChildHolder{
        TextView tvChild;
        public ChildHolder(View view) {
            tvChild = view.findViewById(R.id.tv_child);
        }
    }
}
