package com.example.jack.myapp.demo.RvItemDecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by lcy on 2018/6/19.
 */

public class MyDecoration extends RecyclerView.ItemDecoration {
    Paint dividerPaint;
    int dividerHeight = 3;

    public MyDecoration(Context context, int dividerHeight) {
        dividerPaint = new Paint();
        dividerPaint.setColor(Color.rgb(192,180,186));
        this.dividerHeight = dividerHeight;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + dividerHeight;
            //画了一个矩型
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

//        int childCount = parent.getChildCount();
//        int left = parent.getPaddingLeft();
//        int right = parent.getWidth() - parent.getPaddingRight();
//
//        for (int i = 0; i < childCount - 1; i++) {
//            View view = parent.getChildAt(i);
//            float top = view.getBottom();
//            float bottom = view.getBottom() + dividerHeight;
//            //画了一个矩型
//            c.drawRect(left, top, right, bottom, dividerPaint);
//        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
//      outRect.left = 10;
//      outRect.right = 10;
//        outRect.top = 50;
//        outRect.bottom = 20;
    }
}
