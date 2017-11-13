package com.test.fontfamily;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AppFontActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_font);
    }

    public void open(View view)
    {
        startActivity(new Intent(AppFontActivity.this,AppFontBActivity.class));
    }
}
