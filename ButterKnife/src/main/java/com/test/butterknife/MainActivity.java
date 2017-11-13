package com.test.butterknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
{
    //绑定view
    @BindView(R.id.tv_bk)
    TextView tv_bk;

    //绑定事件
    @OnClick(R.id.btn_click)
    public void click()
    {
        Toast.makeText(MainActivity.this,"Click ButterKnife",Toast.LENGTH_SHORT).show();
    }

    //绑定资源
    @BindString(R.string.butter)
    String butter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tv_bk.setText(butter);
    }
}
