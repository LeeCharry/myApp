package com.example.jack.myapp.demo.head_line;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jack.myapp.R;

import java.util.List;

/**
 * Created by lcy on 2018/7/27.
 */

class MyChannelAdapter extends BaseQuickAdapter<ChannelBean,BaseViewHolder> {

    public MyChannelAdapter(@Nullable List<ChannelBean> data) {
        super(R.layout.item_my_channel, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, final ChannelBean item) {
        helper.setText(R.id.item_tv,item.getTitle());

        ImageView ivDelete = helper.getView(R.id.iv_delete);
        if (item.isToDelete()){
            ivDelete.setVisibility(View.VISIBLE);
        }else{
            ivDelete.setVisibility(View.GONE);
        }
        if (item.getTitle().equals("推荐") || item.getTitle().equals("关注")){
            ivDelete.setVisibility(View.GONE);
        }

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.onDelete(getData().indexOf(item));
                }
            }
        });
    }

    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    interface CallBack{
        void onDelete(int position);
    }

}
