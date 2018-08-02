package com.example.jack.myapp.demo.head_line;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jack.myapp.R;

import java.util.List;

/**
 * Created by lcy on 2018/7/27.
 */

class RecommendChannelAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public RecommendChannelAdapter(@Nullable List<String> data) {
        super(R.layout.item_recommend_channel, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_tv,item);
    }
}
