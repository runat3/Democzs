package com.test.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

public class Brvah extends AppCompatActivity
{

    private RecyclerView rv;
    private ArrayList<Person> data = new ArrayList<>();
    private View view_header;
    private View view_footer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brvah);
        initView();
        initData();
        initAdapter();
    }



    private void initView()
    {


        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        view_header = getLayoutInflater().inflate(R.layout.item_header, (ViewGroup) rv.getParent(), false);
        view_footer = getLayoutInflater().inflate(R.layout.item_footer, (ViewGroup) rv.getParent(), false);

    }

    private void initData()
    {
        for (int i = 0; i < 20; i++)
        {
            Person person = new Person("伞兵" + i + "号", "1388888888" + i, i);
            data.add(person);
        }
        Log.e("data", data.toString());
    }

    private void initAdapter()
    {
        final BrvahAdapter adapter = new BrvahAdapter(R.layout.item_brvah, data);
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);//item加载动画
        adapter.isFirstOnly(false);//item加载动画仅一次（是？否）
        adapter.addHeaderView(view_header);
        adapter.addFooterView(view_footer);
        rv.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener()//上拉加载更多
        {
            @Override
            public void onLoadMoreRequested()
            {
                rv.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        data.add(new Person("张三", "666", 32));
                        data.add(new Person("李四", "777", 12));
                        data.add( new Person("王五", "888", 22));
                        adapter.loadMoreEnd();
                    }
                }, 3000);


            }
        });
    }
}
