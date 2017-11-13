package com.test.fontfamily;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/10/23.
 */

public class CustomTextView extends TextView
{

    public CustomTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    //重写设置字体方法
    @Override
    public void setTypeface(Typeface tf)
    {
        tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/NotoSansCJKsc-Light.otf");
        super.setTypeface(tf);
    }
}
