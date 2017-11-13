package com.test.tab;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{

    private FragmentTabHost mTabHost;
    int[] img = new int[]
            {R.drawable.tab_article, R.drawable.tab_topic, R.mipmap.white, R.drawable.tab_mag, R.drawable.tab_site};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        /**setIndicator:设置tab的样式
         *TabFragment.class：点击tab展现的片段
         *getBundle(0)：传入展现片段的bundle
         */
        mTabHost.addTab(mTabHost.newTabSpec("home").setIndicator(getIndicatorView(0)),
                TabFragment.class, getBundle(0));
        mTabHost.addTab(mTabHost.newTabSpec("find").setIndicator(getIndicatorView(1)),
                TabFragment.class, getBundle(1));
        mTabHost.addTab(mTabHost.newTabSpec("add").setIndicator(getIndicatorView(2)),
                TabFragment.class, getBundle(2));
        mTabHost.addTab(mTabHost.newTabSpec("news").setIndicator(getIndicatorView(3)),
                TabFragment.class, getBundle(3));
        mTabHost.addTab(mTabHost.newTabSpec("mine").setIndicator(getIndicatorView(4)),
                TabFragment.class, getBundle(4));

        ImageView tab_add = (ImageView) findViewById(R.id.tab_add);
        tab_add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //设置tab当前页面
                mTabHost.setCurrentTab(2);
            }
        });
    }

    public View getIndicatorView(int i)
    {
        View view = getLayoutInflater().inflate(R.layout.layout_indicator_view, null);
        ImageView mImageView = (ImageView) view.findViewById(R.id.iv);
        mImageView.setImageResource(img[i]);
        return view;
    }

    public Bundle getBundle(int type)
    {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        return bundle;
    }
}
