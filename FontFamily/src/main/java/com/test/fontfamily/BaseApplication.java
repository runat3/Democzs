package com.test.fontfamily;

import android.app.Application;

/**
 * Created by Administrator on 2017/10/24.
 */

public class BaseApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        FontUtils.getInstance().replaceSystemDefaultFontFromAsset(this,"fonts/NotoSansCJKsc-Thin.otf");
    }
}
