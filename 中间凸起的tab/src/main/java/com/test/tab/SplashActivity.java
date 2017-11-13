package com.test.tab;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity
{

    private Handler mHandler = new Handler();
    private TextView tv_cutdown;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        tv_cutdown = (TextView) findViewById(R.id.tv_cutdown);
        mHandler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        },5000);
        //倒计时
        new CountDownTimer(3000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                tv_cutdown.setText(millisUntilFinished/1000+"秒");
            }

            @Override
            public void onFinish()
            {
                tv_cutdown.setText("跳过");
            }
        }.start();
    }
}
