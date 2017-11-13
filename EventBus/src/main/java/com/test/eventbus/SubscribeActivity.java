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

public class SubscribeActivity extends AppCompatActivity
{

    private TextView tv_message;
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
        tv_message = (TextView) findViewById(R.id.tv_message);
    }

    //接收粘性事件
    public void sticky(View view)
    {
        if (isFirst)
        {
            EventBus.getDefault().register(this);
            isFirst = false;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void Message(MsgStickBiz msgStickBiz)
    {
        tv_message.setText("Message:"+msgStickBiz.getMsg());
    }



    public void send(View view)
    {
        EventBus.getDefault().post(new MsgBiz("I WILL BACk"));
        finish();
    }

    @Override
    protected void onStop()
    {
        super.onStop();

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }
}
