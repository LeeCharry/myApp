package com.example.jack.myapp.demo.recyclerview;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jack.myapp.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by lcy on 2018/4/10.
 */

public class MyAdapter extends BaseQuickAdapter<String,BaseViewHolder> implements OnItemHelperCallBack {
    private List<String> mDatas;
    public MyAdapter(@Nullable List<String> data) {
        super(R.layout.item, data);
        this.mDatas = mData;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item,item);
    }
    @Override
    public void onDrag(int startPoi, int endPoi) {
        Collections.swap(mDatas,startPoi,endPoi);
        notifyItemMoved(startPoi,endPoi);
    }
    @Override
    public void onSwipe(int poition) {
        mDatas.remove(poition);
        notifyItemRemoved(poition);
    }
}

