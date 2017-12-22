package com.test.democzs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/12/22.
 */

public class MyView extends View
{

    private Paint mPaint;
    private float mRadius = 200;

    public MyView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        int height = canvas.getHeight();
        int width = canvas.getWidth();
        // 移动画布到中心位置
        canvas.translate(width / 2, height / 2);
        for (int i = 0; i < 60; i++)
        {
            canvas.drawLine(mRadius-20,0,mRadius,0,mPaint);
            canvas.rotate(6);
        }

    }
}
