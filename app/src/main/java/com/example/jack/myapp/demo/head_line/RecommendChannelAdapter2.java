package com.example.jack.myapp.demo.head_line;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jack.myapp.R;

import java.util.List;

/**
 * Created by lcy on 2018/7/27.
 */

class RecommendChannelAdapter2 extends BaseAdapter {
    private List<String> data;

    public RecommendChannelAdapter2(List<String> data) {
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        RecommendChannelHolder recommendChannelHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend_channel, null);
            recommendChannelHolder = new RecommendChannelHolder(convertView);
            convertView.setTag(recommendChannelHolder);
        } else {
            recommendChannelHolder = (RecommendChannelHolder) convertView.getTag();
        }
        recommendChannelHolder.itemTv.setText(data.get(position));
        return convertView;
    }

    class RecommendChannelHolder {
        TextView itemTv;

        public RecommendChannelHolder(View view) {
            itemTv = view.findViewById(R.id.item_tv);
        }

    }
}
