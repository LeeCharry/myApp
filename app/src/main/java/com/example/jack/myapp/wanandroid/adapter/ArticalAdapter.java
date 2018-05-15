package com.example.jack.myapp.wanandroid.adapter;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jack.myapp.R;
import com.example.jack.myapp.base.BaseApp;
import com.example.jack.myapp.bean.Artical;

import java.util.List;

/**
 * Created by lcy on 2018/4/18.
 */

public class ArticalAdapter extends BaseQuickAdapter<Artical.DatasBean,BaseViewHolder> {
    public ArticalAdapter( @Nullable List<Artical.DatasBean> data) {
        super(R.layout.item_artical, data);
    }
    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void convert(BaseViewHolder helper, Artical.DatasBean item) {
        helper.setText(R.id.tv_author,item.getAuthor().toString());
        helper.setText(R.id.tv_nice_date,item.getNiceDate().toString());
        helper.setText(R.id.tv_title,item.getTitle().toString());
        helper.setText(R.id.tv_chapter_name,item.getChapterName().toString());
        //收藏点击设置
        helper.addOnClickListener(R.id.iv_collect);
        helper.addOnClickListener(R.id.tv_chapter_name);

        ImageView ivCollect = helper.getView(R.id.iv_collect);
        Drawable drawable = ivCollect.getDrawable();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            if (item.isCollect()){
//                drawable.setTint(BaseApp.getAppInstance().getColor(R.color.colorPrimary));
//            }else{
//                drawable.setTint(Color.GRAY);
//            }
//        }else{
//
//        }
        if (item.isCollect()) {
            ivCollect.setImageResource(R.mipmap.ic_collect_blue);
        }else{
            ivCollect.setImageResource(R.mipmap.ic_collect_gray);
        }
    }
    public void isNextLoad(boolean b) {

    }
}
