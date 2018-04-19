package com.example.jack.myapp.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jack.myapp.R;
import com.example.jack.myapp.bean.TreeBean;
import com.example.tulib.util.utils.DeviceUtil;

import java.util.List;

/**
 * Created by lcy on 2018/4/19.
 */

public class TreeAdapter extends BaseQuickAdapter<TreeBean,BaseViewHolder> {
    public TreeAdapter( @Nullable List<TreeBean> data) {
        super(R.layout.item_tree, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TreeBean item) {
        helper.setText(R.id.tv_name,item.getName().toString());
        List<TreeBean.ChildrenBean> children = item.getChildren();

        TextView tvChild = helper.getView(R.id.tv_child);
        StringBuilder stringBuilder = new StringBuilder();
        for (TreeBean.ChildrenBean childrenBean :children
             ) {
            stringBuilder.append(childrenBean.getName()+"    ");
        }
        tvChild.setText(stringBuilder.toString());
//        initChildAdapter(children,helper);
    }

    private void initChildAdapter(List<TreeBean.ChildrenBean> children, BaseViewHolder helper) {
        TreeGridAdapter treeGridAdapter = new TreeGridAdapter(children);
        GridView gridView = helper.getView(R.id.gv_child);
        gridView.setHorizontalSpacing(DeviceUtil.dip2px(mContext,1));
        gridView.setVerticalSpacing(DeviceUtil.dip2px(mContext,1));
        if (children.size()<=3){
            gridView.setNumColumns(3);
        }else{
            gridView.setNumColumns(5);
        }
        gridView.setAdapter(treeGridAdapter);
    }
}
