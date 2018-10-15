package com.example.jack.myapp.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by lcy on 2018/10/9.
 */

public class MyImageview extends ImageView {

    public MyImageview(Context context) {
        super(context);
    }

    public MyImageview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isPressed()) {
            canvas.drawColor(0x33000000);
        }
    }

    /**
     * view被按压的时候执行该方法
     * @param pressed
     */
    @Override
    protected void dispatchSetPressed(boolean pressed) {
        super.dispatchSetPressed(pressed);
        invalidate();
    }
}
