package com.test.fontfamily;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_1 = (TextView) findViewById(R.id.tv_1);
        TextView tv_2 = (TextView) findViewById(R.id.tv_2);
        TextView tv_3 = (TextView) findViewById(R.id.tv_3);
        TextView tv_4 = (TextView) findViewById(R.id.tv_4);
        TextView tv_5 = (TextView) findViewById(R.id.tv_5);
        TextView tv_6 = (TextView) findViewById(R.id.tv_6);
        TextView tv_7 = (TextView) findViewById(R.id.tv_7);
        AssetManager mgr = getAssets();
        Typeface tf1 = Typeface.createFromAsset(mgr, "fonts/NotoSansCJKsc-Thin.otf");
        Typeface tf2 = Typeface.createFromAsset(mgr, "fonts/NotoSansCJKsc-Light.otf");
        Typeface tf3 = Typeface.createFromAsset(mgr, "fonts/NotoSansCJKsc-DemiLight.otf");
        Typeface tf4 = Typeface.createFromAsset(mgr, "fonts/NotoSansCJKsc-Regular.otf");
        Typeface tf5 = Typeface.createFromAsset(mgr, "fonts/NotoSansCJKsc-Medium.otf");
        Typeface tf6 = Typeface.createFromAsset(mgr, "fonts/NotoSansCJKsc-Black.otf");
        Typeface tf7 = Typeface.createFromAsset(mgr, "fonts/NotoSansCJKsc-Bold.otf");
        tv_1.setTypeface(tf1);
        tv_2.setTypeface(tf2);
        tv_3.setTypeface(tf3);
        tv_4.setTypeface(tf4);
        tv_5.setTypeface(tf5);
        tv_6.setTypeface(tf6);
        tv_7.setTypeface(tf7);
    }
}
