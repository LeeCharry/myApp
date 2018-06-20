package com.example.jack.myapp.demo.signature;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lcy on 2018/6/1.
 * 自定义签名控件
 */

public class SignatureView extends View {

    private  int width;  //控件的宽
    private  int height;  //控件的高
    private Context context;
    private Canvas mCanvas;

    private Bitmap mBitmap;  //将签名内容保存到此bitmap上
    private Paint mBitmapPaint;  //画布的画笔
    private Paint mPaint;   //真实的画笔
    private int bitmapH, bitmapW;   //绘制的的bitmap的高度，宽度
    private Path mPath;
    private float tempX, tempY;
    private static String sigPhotoDir = Environment.getExternalStorageDirectory() + File.separator + "signature/";

    private static final float TOUCH_TOLERANCE = 4; //公差
    private File file;

    public SignatureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SignatureView(Context context, int w, int h, OnSavePhotoListener onSavePhotoListener) {
        super(context);
        this.context = context;
        this.bitmapH = h;
        this.bitmapW = w;
        this.onSavePhotoListener = onSavePhotoListener;

        setBackgroundColor(Color.WHITE);
        initCanvas();
    }


    private void initCanvas() {
        setPaintStyle();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);  //画布的画笔
        mBitmap = Bitmap.createBitmap(bitmapW, bitmapH, Bitmap.Config.ARGB_8888);
        mBitmap.eraseColor(Color.argb(0, 0, 0, 0));
        mBitmap.eraseColor(Color.TRANSPARENT);
        mCanvas = new Canvas(mBitmap);  //所有mCanvas画的东西都被保存在了mBitmap中

    }

    /**
     * 清空画板
     */
    public void clear() {
//        mBitmap = Bitmap.createBitmap(bitmapW, bitmapH, Bitmap.Config.RGB_565);
//        mCanvas.setBitmap(mBitmap);  // 重新设置画布，相当于清空画布
        initCanvas();
        invalidate();
    }

    /**
     * 保存签名到SD卡
     */
    public void savePicture() {
        File dir = new File(sigPhotoDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());

        String path = simpleDateFormat.format(date) + ".jpg";
         file = new File(dir, path);

        Bitmap bitmap = getBitmap();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            if (null != onSavePhotoListener) {
                //签名保存成功回调
                onSavePhotoListener.onSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != onSavePhotoListener) {
                //签名保存失败回调
                onSavePhotoListener.onError();
            }
        }
    }

    private void setPaintStyle() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);// 设置外边缘
        mPaint.setStrokeCap(Paint.Cap.ROUND);// 形状
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //在构造方法中获取width和height 为0
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas.drawColor(Color.TRANSPARENT);
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        if (mPath != null) {
            canvas.drawPath(mPath, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath = new Path();  //每次按下，都重新记录path
                mPath.moveTo(x, y);

                tempX = x;
                tempY = y;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                float offX = Math.abs(x - tempX);
                float offY = Math.abs(y - tempY);

                if (offX >= TOUCH_TOLERANCE || offY >= TOUCH_TOLERANCE) {
                    // 从x1,y1到x2,y2画一条贝塞尔曲线，更平滑(直接用mPath.lineTo也可以)
                    mPath.quadTo(tempX, tempY, (x + tempX) / 2, (y + tempY) / 2);
//                    mPath.lineTo(x, y);
                    tempX = x;
                    tempY = y;
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                mPath.lineTo(x, y);
                mCanvas.drawPath(mPath, mPaint);

                mPath = null;   //重新置空
                invalidate();
                break;

        }
        return true;
    }

    private OnSavePhotoListener onSavePhotoListener;

    /**
     * 获取签名bitmap
     *
     * @return
     */
    public Bitmap getBitmap() {
        Bitmap returnBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnBitmap);
        Drawable background = getBackground();
        if (null != background) {
            background.draw(canvas);
        }else{
            canvas.drawColor(Color.WHITE);
        }
        draw(canvas);
        return returnBitmap;
    }

    /**
     * 返回图片路径
     * @return
     */
    public String getPhotoPath() {
        getmBitmap();
        return file.getAbsolutePath().toString();
    }

    /**
     * 返回mBitmap用于显示
     * @return
     */
    public Bitmap getmBitmap() {
        return mBitmap;
    }

    interface OnSavePhotoListener {
        void onSuccess();

        void onError();
    }

}
