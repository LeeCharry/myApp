package com.example.jack.myapp.wanandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jack.myapp.R;
import com.example.jack.myapp.bean.TreeBean;

import java.util.List;

/**
 * Created by lcy on 2018/4/19.
 */

public class TreeGridAdapter extends BaseAdapter {
    private List<TreeBean.ChildrenBean> list;
    private Context context;

    public TreeGridAdapter(List<TreeBean.ChildrenBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context = parent.getContext();
        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_treegrid,null);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position).getName());

        return convertView;
    }
    class ViewHolder{
        TextView textView;
    }
}
