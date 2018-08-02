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
import java.util.HashMap;
import java.util.List;

/**
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
    private HashMap<Integer, Point> selectedPointMap = new HashMap<>(); //选中的圆心点map
    //    private Path path;  //拖动轨迹
    private int startCircleIndex = -1;  //开始触摸的圆心索引


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
            paint.setStrokeWidth(3);
            setSolidPaint(Paint.Style.STROKE);
            canvas.drawCircle(cx, cy, itemRadis, paint);

            if (selectedPointList.size() > 0) {

            }

        }

            if (selectedPointList.size() > 0) {

                    for (int j = 0; j < selectedPointList.size(); j++) {
                        for (int i = 0; i < 9; i++) {
                        if (selectedPointMap.get(i) == selectedPointList.get(j)) {
                            setSolidPaint(Paint.Style.FILL_AND_STROKE);
                            paint.setStrokeWidth(3);
//                            canvas.drawCircle(cx, cy, innerItemRadis, paint);
                        }

                        paint.setStrokeWidth(7);
                        if (j == selectedPointList.size() - 1) {
                            return;
                        } else {
                            canvas.drawLine(selectedPointList.get(j).x, selectedPointList.get(j).y, selectedPointList.get(j + 1).x, selectedPointList.get(j + 1).y, paint);
                        }
                }
                }
            }

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
                for (int i = 0; i < pointList.size(); i++) {
                    if (isInCircle(point, pointList.get(i))) {
                        startCircleIndex = i;
                        selectedPointList.add(pointList.get(i));
                        selectedPointMap.put(i, pointList.get(i));
                        invalidate();  //重新绘制
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (selectedPointList.size() >= 1) {
                    for (int i = 0; i < pointList.size(); i++) {
                        if (isInInnerCircle(point, pointList.get(i))) {
                            selectedPointList.add(pointList.get(i));
                            selectedPointMap.put(i, pointList.get(i));
                            invalidate();  //重新绘制
                        }else{
                            if (i == pointList.size()-1){
//                                tempX = rawX;
//                                tempY = rawY;
                            }
                        }
                    }
//                    selectedPointList.add(1,new Point(rawX,rawY));
//                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (selectedPointList.size() != 4) {
                    ToastUtils.showShort("密码错误，请重新输入");
                }
                selectedPointList.clear();
                selectedPointMap.clear();
                invalidate();
                break;
        }
        return true;
    }

    private boolean isInCircle(Point point, Point point1) {
        double sqrt = Math.sqrt(Math.pow((point.x - point1.x), 2) + Math.pow((point.y - point1.y), 2));
        return sqrt <= itemRadis;
    }

    private boolean isInInnerCircle(Point point, Point point1) {
        double sqrt = Math.sqrt(Math.pow((point.x - point1.x), 2) + Math.pow((point.y - point1.y), 2));
        return sqrt <= innerItemRadis;
    }

    public float dpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }


}
