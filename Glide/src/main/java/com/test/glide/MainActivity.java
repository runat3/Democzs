package com.test.glide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static com.test.glide.GlideOptions.bitmapTransform;

public class MainActivity extends AppCompatActivity
{

    private String url ="http://cn.bing.com/az/hprichbg/rb/TurpanDepression_ZH-CN12295576336_1920x1080.jpg";
    private String gifurl ="http://p1.pstatp.com/large/166200019850062839d3";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        GlideApp.with(this)
                .load(gifurl)//资源路径
                .error(R.mipmap.ic_launcher)//加载失败
                .placeholder(R.mipmap.ic_launcher)//占位符
                .circleCrop()
//                .apply(bitmapTransform(new CropCircleTransformation()))//图片转换
                .into(iv);
        Log.e("Glide", "onCreate: ");
    }

}
