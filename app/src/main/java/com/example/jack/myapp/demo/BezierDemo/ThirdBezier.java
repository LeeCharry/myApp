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
 * 三阶贝赛尔view
 * Created by lcy on 2018/8/21.
 */

public class ThirdBezier extends View {
    private Paint mPaint;
    private float centerY,centerX;
    private float[] startPoint = new float[2];
    private float[] endPoint = new float[2];
    private float[] controlPoint1 = new float[2];
    private float[] controlPoint2 = new float[2];
    private boolean mode = true;

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public ThirdBezier(Context context) {
        this(context,null);
    }
    public ThirdBezier(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public ThirdBezier(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        controlPoint1[0] = centerX-200;
        controlPoint1[1] = centerY-200;
        controlPoint2[0] = centerX-200;
        controlPoint2[1] = centerY-200;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mode) {
            controlPoint1[0] = event.getX();
            controlPoint1[1] = event.getY();
        }else{
            controlPoint2[0] = event.getX();
            controlPoint2[1] = event.getY();
        }
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
        canvas.drawPoints(controlPoint1,mPaint);
        canvas.drawPoints(controlPoint2,mPaint);

        //画辅助线
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.LTGRAY);
        canvas.drawLine(startPoint[0],startPoint[1],controlPoint1[0],controlPoint1[1],mPaint);
        canvas.drawLine(controlPoint1[0],controlPoint1[1],controlPoint2[0],controlPoint2[1],mPaint);
        canvas.drawLine(endPoint[0],endPoint[1],controlPoint2[0],controlPoint2[1],mPaint);

        //画三阶贝赛尔曲线
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.RED);
        Path path = new Path();
        path.moveTo(startPoint[0],startPoint[1]);
        path.cubicTo(controlPoint1[0],controlPoint1[1],controlPoint2[0],controlPoint2[1],endPoint[0],endPoint[1]);
//      path.quadTo(controlPoint[0],controlPoint[1],endPoint[0],endPoint[1]);
        canvas.drawPath(path,mPaint);
    }
}
