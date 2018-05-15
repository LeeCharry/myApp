package com.example.jack.myapp.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jack.myapp.R;
import com.example.jack.myapp.bean.Artical;

import java.util.List;

/**
 * Created by lcy on 2018/4/18.
 */

public class ArticalAdapter2 extends BaseQuickAdapter<Artical.DatasBean,BaseViewHolder> {
    public ArticalAdapter2(@Nullable List<Artical.DatasBean> data) {
        super(R.layout.item_artical, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Artical.DatasBean item) {
        helper.setText(R.id.tv_author,item.getAuthor().toString());
        helper.setText(R.id.tv_nice_date,item.getNiceDate().toString());
        helper.setText(R.id.tv_title,item.getTitle().toString());
//        helper.setText(R.id.tv_chapter_name,item.getChapterName().toString());
        helper.getView(R.id.tv_chapter_name).setVisibility(View.INVISIBLE);
        //收藏点击设置
        helper.addOnClickListener(R.id.iv_collect);

        ImageView ivCollect = helper.getView(R.id.iv_collect);
        if (item.isCollect()) {
            ivCollect.setImageResource(R.mipmap.ic_collect_blue);
        }else{
            ivCollect.setImageResource(R.mipmap.ic_collect_gray);
        }
    }
    public void isNextLoad(boolean b) {

    }
}
