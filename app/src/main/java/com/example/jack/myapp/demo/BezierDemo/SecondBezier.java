package com.example.jack.myapp.demo.BezierDemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 二阶贝赛尔view
 * Created by lcy on 2018/8/21.
 */

public class SecondBezier extends View {
    private Paint mPaint;
    private float centerY,centerX;
    private float[] startPoint = new float[2];
    private float[] endPoint = new float[2];
    private float[] controlPoint = new float[2];

    public SecondBezier(Context context) {
        this(context,null);
    }
    public SecondBezier(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public SecondBezier(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
         mPaint = new Paint();
         mPaint.setColor(Color.GREEN);
         mPaint.setStyle(Paint.Style.STROKE);
         mPaint.setStrokeWidth(20);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w/2;
        centerY = h/2;

        startPoint[0] = centerX-200;
        startPoint[1] = centerY;
        endPoint[0] = centerX+200;
        endPoint[1] = centerY;

        controlPoint[0] = centerX;
        controlPoint[1] = centerY-100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        controlPoint[0] = event.getX();
        controlPoint[1] = event.getY();

        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画数据点和控制点
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(40);
        canvas.drawPoints(startPoint,mPaint);
        canvas.drawPoints(endPoint,mPaint);
        canvas.drawPoints(controlPoint,mPaint);

        //画辅助线
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.LTGRAY);
        canvas.drawLine(startPoint[0],startPoint[1],controlPoint[0],controlPoint[1],mPaint);
        canvas.drawLine(endPoint[0],endPoint[1],controlPoint[0],controlPoint[1],mPaint);

        //画二阶贝赛尔曲线
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.RED);
        Path path = new Path();
        path.moveTo(startPoint[0],startPoint[1]);
        path.quadTo(controlPoint[0],controlPoint[1],endPoint[0],endPoint[1]);
        canvas.drawPath(path,mPaint);
    }
}
