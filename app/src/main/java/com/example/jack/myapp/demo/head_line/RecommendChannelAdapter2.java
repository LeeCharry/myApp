package com.example.jack.myapp.demo.head_line;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jack.myapp.R;

import java.util.List;

/**
 * Created by lcy on 2018/7/27.
 */

class RecommendChannelAdapter2 extends BaseAdapter {
    private List<String> data;
    public boolean deleteFlag = false;
    public int removeIndex;
    private boolean isVisible = true;

    /** 要删除的position */
    public int remove_position = -1;
    /** 设置删除的position */
    public void setRemove(int position) {
        remove_position = position;
        notifyDataSetChanged();
        // notifyDataSetChanged();
    }
    /** 删除频道列表 */
    public void remove() {
        data.remove(remove_position);
        remove_position = -1;
        notifyDataSetChanged();
    }

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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        RecommendChannelHolder recommendChannelHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend_channel, null);
            recommendChannelHolder = new RecommendChannelHolder(convertView);
            convertView.setTag(recommendChannelHolder);
        } else {
            recommendChannelHolder = (RecommendChannelHolder) convertView.getTag();
        }


        if (deleteFlag) {
            if (position >= removeIndex){
                convertView.setVisibility(View.INVISIBLE);
                final View finalConvertView = convertView;
                TranslateAnimation translateAnimation = null;
                if ((position+1)%4==0) {
                    translateAnimation = new TranslateAnimation(-3*convertView.getWidth(), 0, convertView.getHeight(), 0);
                }else{
                    translateAnimation = new TranslateAnimation(convertView.getWidth(), 0, 0, 0);
                }
//               TranslateAnimation translateAnimation = new TranslateAnimation(convertView.getWidth(), 0, 0, 0);
                translateAnimation.setDuration(200);
                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        finalConvertView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                convertView.startAnimation(translateAnimation);
                if (position == data.size()-1){
                    deleteFlag = false;
                }
            }
        }

        recommendChannelHolder.itemTv.setText(data.get(position));

        if (!isVisible && (position == -1 + data.size())){
            recommendChannelHolder.itemTv.setText("");
            recommendChannelHolder.itemTv.setSelected(true);
            recommendChannelHolder.itemTv.setEnabled(true);
        }
        return convertView;
    }


    class RecommendChannelHolder {
        TextView itemTv;

        public RecommendChannelHolder(View view) {
            itemTv = view.findViewById(R.id.item_tv);
        }

    }

    /** 设置是否可见 */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
