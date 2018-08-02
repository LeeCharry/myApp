package com.example.jack.myapp.demo.head_line;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jack.myapp.R;

import java.util.List;

/**
 * Created by lcy on 2018/7/27.
 */

class MyChannelAdapter2 extends BaseAdapter {
    private List<ChannelBean> data;

    private CallBack callBack;

    public MyChannelAdapter2(List<ChannelBean> data) {
        this.data = data;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
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
        MyChannelHolder myChannelHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_channel,null);
            myChannelHolder = new MyChannelHolder(convertView);
            convertView.setTag(myChannelHolder);
        }else{
            myChannelHolder = (MyChannelHolder) convertView.getTag();
        }
        ChannelBean channelBean = data.get(position);
        myChannelHolder.itemTv.setText(channelBean.getTitle().toString());
        if (channelBean.isToDelete()){
            myChannelHolder.ivDelete.setVisibility(View.VISIBLE);
        }else{
            myChannelHolder.ivDelete.setVisibility(View.GONE);
        }
        if (channelBean.getTitle().equals("推荐") || channelBean.getTitle().equals("关注")){
            myChannelHolder.ivDelete.setVisibility(View.GONE);
        }

        myChannelHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.onDelete(position);
                }
            }
        });
        return convertView;
    }

    class MyChannelHolder{
         TextView itemTv;
         ImageView ivDelete;

        public MyChannelHolder(View view) {
            itemTv = view.findViewById(R.id.item_tv);
            ivDelete = view.findViewById(R.id.iv_delete);
        }
    }
    interface CallBack{
        void onDelete(int position);
    }
}
