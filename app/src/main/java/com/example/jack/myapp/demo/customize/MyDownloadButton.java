package com.example.jack.myapp.demo.customize;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * 自定义下载进度按钮
 * Created by lcy on 2018/8/3.
 */

public class MyDownloadButton extends Button {
    private float downloadingPer;
    private Context context;
    public static final int STATE_NORMAL = 0;  //正产状态
    public static final int STATE_DOWNING = 1;  //下载中状态
    public static final int STATE_COMPLETE = 2;  //下载完成状态
    private int buttonState = STATE_NORMAL;  //记录button的状态
    private float buttonWidth;
    private float buttonHeight;
    private Paint paint;
    private Paint textPaint;
    private float yRadius ;
    private float xRadius;
    private String TEXTMSG = "";
    private int progress = 0;
    private MyDownloadButton downloadButton;

    public MyDownloadButton(Context context) {
        this(context,null);
    }

    public MyDownloadButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }
    private void initPaint() {
        paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.LTGRAY);

        textPaint = new Paint();
        textPaint.setStrokeWidth(2);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(dpToPx(context,16));
        textPaint.setColor(Color.WHITE);
    }

    public MyDownloadButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.downloadButton = this;
        initPaint();

        /**
         * 点击事件
         */
        downloadButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onMyDownloadButtonOnClickListener) {
                    onMyDownloadButtonOnClickListener.onClick(downloadButton, buttonState);
                }
            }
        });
    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
////        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
//
//    }

    private int measureHeight(int heightMeasureSpec) {
        int result = 0;
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightMeasureSize = MeasureSpec.getSize(heightMeasureSpec);

        if (heightMeasureMode == MeasureSpec.EXACTLY){
            result = heightMeasureSize + getPaddingTop() + getPaddingBottom();
        }
//        else{
//            result = getDefaultSize()
//        }
        return result;
    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthMeasureSize= MeasureSpec.getSize(widthMeasureSpec);

        if (widthMeasureMode == MeasureSpec.EXACTLY){
            result = widthMeasureSize + getPaddingLeft() + getPaddingRight();
        }

        return result;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
//        setLeft(getLeft());
//        setRight(getRight());
//        setTop(getTop());
//        setBottom(getBottom());
//        requestLayout();
//        ViewGroup.LayoutParams params = getLayoutParams();
//        buttonWidth =  params.width;
//        buttonHeight = params.height;
//            setMargins(this,0,0,0,0);
//        final int paddingLeft = getPaddingLeft();
//        final int paddingRight = getPaddingRight();
//        final int paddingTop = getPaddingTop();
//        final int paddingBottom = getPaddingBottom();

        super.onDraw(canvas);
//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
//        layoutParams.setMargins(0,0,0,0);
//        int leftMargin = layoutParams.leftMargin;
//        int topMargin = layoutParams.topMargin;
//        setPadding(0,0,0,0);

        buttonWidth = getWidth();

        buttonHeight = getHeight();

        downloadingPer = buttonWidth / 100;
        yRadius = buttonHeight / 2;
        xRadius = yRadius;

//        int left = getLeft() ;
//        int top = getTop() ;
//        int right = getRight();
//        int bottom = getBottom();
////        RectF rectF = new RectF(left, top, right, bottom);
        RectF rectF = new RectF(0, 0, getWidth(), getHeight());

        paint.setColor(Color.LTGRAY);
        Rect rect = null;
        switch (buttonState) {
            case STATE_NORMAL:
                canvas.drawRoundRect(rectF, xRadius, yRadius, paint);
                TEXTMSG = "下载";
                 rect = new Rect();
                textPaint.getTextBounds(TEXTMSG,0,TEXTMSG.length(),rect);

//                canvas.drawText(TEXTMSG, left + (buttonWidth - rect.width()) / 2, top + (buttonHeight+rect.height())/2, textPaint);
                canvas.drawText(TEXTMSG, (buttonWidth - rect.width()) / 2, (buttonHeight+rect.height())/2, textPaint);
                break;
            case STATE_DOWNING:
                //灰色圆角
                canvas.drawRoundRect(rectF, xRadius, yRadius, paint);
                //进度文字
                float downloadingWidth = downloadingPer * progress;
                 rect = new Rect();
                textPaint.getTextBounds(String.valueOf(downloadingWidth),0,String.valueOf(downloadingWidth).length(),rect);
                //绿色已下载部分
                paint.setColor(Color.GREEN);
//                RectF rectFCompleted = new RectF(left, top, left + downloadingWidth, bottom);
                RectF rectFCompleted = new RectF(0, 0, downloadingWidth, getHeight());
                canvas.drawRoundRect(rectFCompleted, xRadius, yRadius, paint);

                //最后写进度
//                canvas.drawText(String.valueOf(progress) + "%", left + (buttonWidth - rect.width()) / 2,top + (buttonHeight+rect.height())/2, textPaint);
                canvas.drawText(String.valueOf(progress) + "%", (buttonWidth - rect.width()) / 2,(buttonHeight+rect.height())/2, textPaint);

                break;
            case STATE_COMPLETE:
                paint.setColor(Color.GREEN);
                canvas.drawRoundRect(rectF, xRadius, yRadius, paint);


                TEXTMSG = "安装";
                rect = new Rect();
                textPaint.getTextBounds(TEXTMSG,0,TEXTMSG.length(),rect);

//                canvas.drawText(TEXTMSG, left + (buttonWidth - rect.width()) / 2,top + (buttonHeight + rect.height())/2, textPaint);
                canvas.drawText(TEXTMSG, (buttonWidth - rect.width()) / 2,(buttonHeight + rect.height())/2, textPaint);
                break;
        }
    }

    /**
     * 设置进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        this.progress = progress;

    }

    public void setButtonState(int buttonState) {
        this.buttonState = buttonState;
        invalidate();
    }

    public float dpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public void setOnMyDownloadButtonOnClickListener(OnMyDownloadButtonOnClickListener onMyDownloadButtonOnClickListener) {
        this.onMyDownloadButtonOnClickListener = onMyDownloadButtonOnClickListener;
    }
    private OnMyDownloadButtonOnClickListener onMyDownloadButtonOnClickListener;

    interface OnMyDownloadButtonOnClickListener {
        void onClick(View v, int buttonState);
    }
}
