package com.example.jack.myapp.demo.date;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lcy on 2018/4/13.
 */

public class DateGridAdapter extends BaseAdapter {
    private List<String> list;
    public DateGridAdapter(List<String> list) {
        this.list = list;
    }
    @Override
    public int getCount() {
        return 12;
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
        ViewHolder viewHolder;
        if (convertView == null){

        }
        return null;
    }
    class ViewHolder{
        TextView tvMonth;
        List<TextView> textViews;
        public ViewHolder(TextView tvMonth, List<TextView> textViews) {
            this.tvMonth = tvMonth;
            this.textViews = textViews;
        }
    }
}
