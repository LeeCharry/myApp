package com.example.jack.myapp.demo.circleProgressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.jack.myapp.R;

/**
 * Created by lcy on 2018/9/21.
 * 自定义圆环进度条
 */

public class CircleProgressBar extends View {
    private  Context context;
    private Paint paint;
    private Paint textPaint;
    private int greyRadius;
//    private int blueRadius;
    private int whiteRadius;
    private RectF rectF;
    private String lefttimeStr = "24:00";
    private float progress;
    private int sweepAngle = 0;
    private Long totalTime = 24 * 60 * 60 * 1000L ;

    public CircleProgressBar(Context context) {
        this(context,null);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
         paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
         rectF = new RectF();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        greyRadius = getWidth()/2;
        whiteRadius = greyRadius-16;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);

        /**
         * 1、先画灰色的底圆
         * 2、再画蓝色圆弧
         * 3、然后画白色的内部圆
         * 4、最后在圆心处写当前审批剩余时间  比如：22：00
         */
        paint.setColor(getResources().getColor(R.color.light_grey));
        canvas.drawCircle(getWidth()/2,getHeight()/2,greyRadius,paint);

        paint.setColor(getResources().getColor(R.color.light_green));
        rectF.set(0,0,getWidth(),getHeight());
        canvas.drawArc(rectF,-90,sweepAngle,true,paint);

        paint.setColor(Color.WHITE);
        canvas.drawCircle(getWidth()/2,getHeight()/2,whiteRadius,paint);


        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(dpToPx(context,16));
        Rect rect = new Rect();
        paint.getTextBounds(lefttimeStr,0,lefttimeStr.length(),rect);
        canvas.drawText(lefttimeStr,(getWidth()-rect.width())/2,(getHeight()+rect.height())/2,paint);

    }

    public float dpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    /**
     * 设置进度
     * @param progress
     *
     */
    public void setProgress(float progress) {
        this.progress = progress;
        Long leftMilliSeconds = (long)(totalTime * (1 - progress / 100));   //剩余毫秒数

        lefttimeStr = getimeStr(leftMilliSeconds);
        sweepAngle = (int) (progress/100 * 360);
        invalidate();
    }

    private String getimeStr(Long leftMilliSeconds) {
//        Date date = new Date(leftMilliSeconds);
        long l = leftMilliSeconds / (1000 * 60);
        String hour = l / 60 == 0 ? "00" : String.valueOf(1/60);
        String minute = l % 60 == 0 ? "00" : String.valueOf(1%60);
        return hour.concat(":").concat(minute);
    }
}
