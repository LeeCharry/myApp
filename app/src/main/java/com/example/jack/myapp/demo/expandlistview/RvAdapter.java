package com.example.jack.myapp.demo.expandlistview;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jack.myapp.R;

import java.util.List;

/**
 * Created by lcy on 2018/5/10.
 */

public class RvAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

    public RvAdapter( @Nullable List<String> data) {
        super(R.layout.item_group, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView textView = helper.getView(R.id.tv_parent);
        textView.setText(item);
    }
}
