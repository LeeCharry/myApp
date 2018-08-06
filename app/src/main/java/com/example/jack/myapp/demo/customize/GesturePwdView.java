package com.example.jack.myapp.demo.customize;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义手势密码解锁控件
 * Created by lcy on 2018/8/2.
 */

public class GesturePwdView extends View {
    private int centerX;   //中心点x坐标
    private int centerY;
    private int centerInterval; //相邻两圆心的距离
    private Context context;
    private int itemSpacec = 80;  //相邻两园的间距（）
    private int itemRadis = 30;   //圆半径
    private int innerItemRadis = 12;   //小圆的半径
    private Paint paint;
    private List<Point> pointList = new ArrayList(); //九个圆心点集合
    private List<Point> selectedPointList = new ArrayList(); //选中的圆心点集合
    private int startCircleIndex = -1;  //开始触摸的圆心索引
    private int tempX;
    private int tempY;
    private boolean isStartSliding = false;  //记录第一个画的圆心点，down-->不需要画线,,move--->需要画线
    private String pass = "03678";   //设定的密码


    public GesturePwdView(Context context) {
        super(context);
        this.context = context;
        initPaint();
    }

    public GesturePwdView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initPaint();

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        centerX = dm.widthPixels / 2;
        centerY = dm.heightPixels / 2;

        itemSpacec = (int) dpToPx(context, itemSpacec);
        itemRadis = (int) dpToPx(context, itemRadis);
        innerItemRadis = (int) dpToPx(context, innerItemRadis);

        centerInterval = itemSpacec + itemRadis;
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
    }

    public GesturePwdView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //首先绘制九个圆环 ？？  -----------done
        pointList.clear();
        for (int i = 0; i < 9; i++) {
            int cx = centerX + (i % 3 - 1) * centerInterval;
            int cy = centerY + (i / 3 - 1) * centerInterval;
            pointList.add(new Point(cx, cy));

            if (selectedPointList.size() > 0) {
                for (int j = 0; j < selectedPointList.size(); j++) {
                    int x = selectedPointList.get(j).x;
                    int y = selectedPointList.get(j).y;
                    if (x == cx && y == cy) {
                        setSolidPaint(Paint.Style.FILL_AND_STROKE);
                        paint.setStrokeWidth(3);
                        canvas.drawCircle(cx, cy, innerItemRadis, paint);
                    }
                    paint.setStrokeWidth(6);
                    if (j == selectedPointList.size() - 1) {
                        if (!isStartSliding) {
                            canvas.drawLine(selectedPointList.get(j).x, selectedPointList.get(j).y, tempX, tempY, paint);
                        }
                    } else {
                        //只画一个点的时候，不需要画直线
                        canvas.drawLine(selectedPointList.get(j).x, selectedPointList.get(j).y, selectedPointList.get(j + 1).x, selectedPointList.get(j + 1).y, paint);
                    }
                }
            }
            paint.setStrokeWidth(3);
            setSolidPaint(Paint.Style.STROKE);
            canvas.drawCircle(cx, cy, itemRadis, paint);
        }
//        for (int i = 0; i < pointList.size() ; i++) {
//
//        }

//        if (selectedPointList.size() > 0) {
//            for (int j = 0; j < selectedPointList.size(); j++) {
//                for (int i = 0; i < 9; i++) {
//                    if (selectedPointMap.get(i) == selectedPointList.get(j)) {
//                        setSolidPaint(Paint.Style.FILL_AND_STROKE);
//                        paint.setStrokeWidth(3);
////                            canvas.drawCircle(cx, cy, innerItemRadis, paint);
//                    }
//                    paint.setStrokeWidth(7);
//                    if (j == selectedPointList.size() - 1) {
//                        return;
//                    } else {
//                        canvas.drawLine(selectedPointList.get(j).x, selectedPointList.get(j).y, selectedPointList.get(j + 1).x, selectedPointList.get(j + 1).y, paint);
//                    }
//                }
//            }
//        }

    }

    private void setSolidPaint(Paint.Style fillAndStroke) {
        paint.setStyle(fillAndStroke);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        Point point = new Point(rawX, rawY);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isStartSliding = true;
                for (int i = 0; i < pointList.size(); i++) {
                    if (isInCircle(point, pointList.get(i))) {
                        startCircleIndex = i;
                        selectedPointList.add(pointList.get(i));
                        invalidate();  //重新绘制
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                isStartSliding = false;
                if (selectedPointList.size() >= 1) {
                    for (int i = 0; i < pointList.size(); i++) {
                        if (isInInnerCircle(point, pointList.get(i)) && !selectedPointList.contains(pointList.get(i))) {
                            selectedPointList.add(pointList.get(i));
                        }
                    }
                    tempX = rawX;
                    tempY = rawY;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isCorrect(selectedPointList)) {
                    ToastUtils.showShort("恭喜你，密码输入正确！");
                }
                selectedPointList.clear();
                invalidate();
                break;
        }
        return true;
    }

    private boolean isCorrect(List<Point> selectedPointList) {
        if (selectedPointList.size() < 4) {
            ToastUtils.showShort("密码错误至少四位");
            return false;
        }
        StringBuilder tempPass = new StringBuilder();
        for (int j = 0; j < selectedPointList.size(); j++) {
            for (int i = 0; i < pointList.size(); i++) {
                if (selectedPointList.get(j).x == pointList.get(i).x && selectedPointList.get(j).y == pointList.get(i).y) {
                    tempPass.append(i);
                }
            }
        }
        if (!tempPass.toString().trim().equals(pass)) {
            ToastUtils.showShort("密码错误，请重新输入");
            return false;
        }
        return true;
    }

    /**
     * 触摸点是否在大圆内（第一个点的判断条件）
     *
     * @param point
     * @param point1
     * @return
     */
    private boolean isInCircle(Point point, Point point1) {
        double sqrt = Math.sqrt(Math.pow((point.x - point1.x), 2) + Math.pow((point.y - point1.y), 2));
        return sqrt <= itemRadis;
    }

    /**
     * 触摸点是否在小圆内
     *
     * @param point
     * @param point1
     * @return
     */
    private boolean isInInnerCircle(Point point, Point point1) {
        double sqrt = Math.sqrt(Math.pow((point.x - point1.x), 2) + Math.pow((point.y - point1.y), 2));
//        return sqrt <= innerItemRadis;
        return sqrt <= dpToPx(context, 18);
    }

    public float dpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }


}
