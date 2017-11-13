package com.test.mdui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{


    private Button button1;
    private static final String TAG = "MainActivity";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Button button1 = (Button) findViewById(R.id.button1);
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent();
        switch (v.getId())
        {
            case R.id.button1:
                intent.setClass(MainActivity.this, LoginActivity.class);
                break;
            case R.id.button2:
                intent.setClass(MainActivity.this, NavigationDrawerActivity.class);
                break;
            case R.id.button3:
                intent.setClass(MainActivity.this, ScrollingActivity.class);
                break;
            case R.id.button4:
                intent.setClass(MainActivity.this, SettingsActivity.class);
                break;
            case R.id.button5:
                intent.setClass(MainActivity.this, TabsActivity.class);
                break;
            case R.id.button:
                intent.setClass(MainActivity.this,ConstraintLayoutActivity.class);
                break;
            default:
                break;

        }
        startActivity(intent);
    }
}
