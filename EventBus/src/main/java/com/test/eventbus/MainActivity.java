package com.test.eventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.test.eventbus.model.MsgBiz;
import com.test.eventbus.model.MsgStickBiz;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity
{

    private TextView tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_message = (TextView) findViewById(R.id.tv_message);
        //1.注册
        EventBus.getDefault().register(MainActivity.this);
    }

    //2.订阅消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Messagedd(MsgBiz msgBiz)
    {
        tv_message.setText("Message:" + msgBiz.getMsg());
    }

    public void send(View view)
    {

        startActivity(new Intent(MainActivity.this, SubscribeActivity.class));
    }


    //发送粘性事件
    public void sendSticky(View view)
    {
        EventBus.getDefault().postSticky(new MsgStickBiz("我是粘性事件"));
        startActivity(new Intent(MainActivity.this, SubscribeActivity.class));
    }

    @Override
    protected void onStop()
    {
        super.onStop();

//        EventBus.getDefault().unregister(MainActivity.this);
    }

    @Override
    protected void onDestroy()
    {
        EventBus.getDefault().unregister(MainActivity.this);
        super.onDestroy();
    }
}
