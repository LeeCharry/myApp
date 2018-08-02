package com.example.jack.myapp.demo.customize;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.blankj.utilcode.util.LogUtils;
import com.example.jack.myapp.AppConstant;
import com.example.jack.myapp.R;

/**
 * Created by lcy on 2018/8/1.
 */

public class CircleView extends View {
    private final int default_circle_color = 0x88ff0000;
    private final float default_circle_radius = 30;
    private int circleColor = Color.parseColor("#88ff0000");
    private float circleRadius = 30;
    private Paint paint;
    private float rawX = 100;
    private float rawY = 100;
    private Integer STATE = -1;
    private Integer IS_PLAYING = 0;
    private Integer IS_STOPED = 1;
//    private RotateAnimation rotateAnimation;
    private long offset;
//    private float angle2 = 0;
//    private float angle = 0;
    private ObjectAnimator animator;
//    private RotateAnimation rotateAnimation;

    public CircleView(Context context) {
        super(context);
        LogUtils.a(AppConstant.TAG,"--------------------------CircleView1");
        initOption(context);
    }

    private void initOption(Context context) {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LogUtils.a(AppConstant.TAG,"--------------------------CircleView2");
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        circleRadius = ta.getDimension(R.styleable.CircleView_radius, default_circle_radius);
        circleColor = ta.getColor(R.styleable.CircleView_circleColor, default_circle_color);
        ta.recycle();
        initOption(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogUtils.a(AppConstant.TAG,"--------------------------CircleView3");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtils.a(AppConstant.TAG,"--------------------------onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtils.a(AppConstant.TAG,"--------------------------onLayout");
    }


    @Override
    protected void onDraw(Canvas canvas) {
        LogUtils.a(AppConstant.TAG,"--------------------------onDraw");
        super.onDraw(canvas);
//        canvas.rotate(angle2,getWidth()/2,getHeight()/2);
            //设置画笔颜色
            paint.setColor(circleColor);

            int width = getWidth();
            int height = getHeight();
            canvas.drawCircle(width / 2, height / 2, circleRadius, paint);
            paint.setColor(Color.rgb(255, 255, 255));
            paint.setStrokeWidth(3);

            paint.setTextSize(40);
            String testString = "移动门户";
            float textWidth = paint.measureText(testString);
            canvas.drawText(testString, (width - textWidth) / 2, height / 2 + 10, paint);


    }

    //旋转动画
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void startPlay() {
        if (null == animator) {
          startRotate();
        }else{
            if (STATE == IS_PLAYING) {
//                angle2 = (angle2 + angle)%360;//可以取余也可以不取，看实际的需求
                STATE = IS_STOPED;
                animator.pause();
//                rotateAnimation.cancel();
                invalidate();
            }else if (STATE == IS_STOPED){
//                rotateAnimation.start();
                animator.resume();
                STATE = IS_PLAYING;
            }
        }
    }

    private void startRotate() {
//        rotateAnimation = new RotateAnimation(0, 360, getWidth() / 2, getHeight() / 2){
//            @Override
//            protected void applyTransformation(float interpolatedTime, Transformation t) {
//                super.applyTransformation(interpolatedTime, t);
//                angle = interpolatedTime * 360;
//            }
//        };
//        rotateAnimation.setDuration(3000);
//        rotateAnimation.setRepeatCount(Animation.INFINITE);
//        rotateAnimation.setFillAfter(true);
//        rotateAnimation.setRepeatMode(RotateAnimation.RESTART);
//        startAnimation(rotateAnimation);
        STATE = IS_PLAYING;


        animator = ObjectAnimator.ofFloat(this, "rotation", 0f, 360.0f);
        animator.setDuration(3000);
        animator.setInterpolator(new LinearInterpolator());//不停顿
        animator.setRepeatCount(-1);//设置动画重复次数
        animator.setRepeatMode(ValueAnimator.RESTART);//动画重复模式
        animator.start();
    }

}
