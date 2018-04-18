package com.example.jack.myapp.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jack.myapp.R;
import com.example.jack.myapp.bean.Artical;

import java.util.List;

/**
 * Created by lcy on 2018/4/18.
 */

public class ArticalAdapter extends BaseQuickAdapter<Artical.DatasBean,BaseViewHolder> {
    public ArticalAdapter( @Nullable List<Artical.DatasBean> data) {
        super(R.layout.item_artical, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Artical.DatasBean item) {

        helper.setText(R.id.tv_author,item.getAuthor().toString());
        helper.setText(R.id.tv_nice_date,item.getNiceDate().toString());
        helper.setText(R.id.tv_title,item.getTitle().toString());
        helper.setText(R.id.tv_chapter_name,item.getChapterName().toString());

    }
}
