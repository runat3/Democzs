package com.test.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button btn_gv;
    private Button btn_stagger;
    private Button btn_add;
    private Button btn_delete;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    private ArrayList<String> data = new ArrayList<String>();
    private Button btn_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();


    }

    private void initData()
    {
        for (int i = 'A'; i <= 'z'; i++)
        {
            data.add(" "+(char)i);
        }
    }

    private void initView()
    {
//        btn_lv = (Button) findViewById(R.id.btn_lv);
        btn_lv = (Button) findViewById(R.id.btn_lv);
        btn_gv = (Button) findViewById(R.id.btn_gv);
        btn_stagger = (Button) findViewById(R.id.btn_stagger);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        btn_lv.setOnClickListener(this);
        btn_gv.setOnClickListener(this);
        btn_stagger.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_delete.setOnClickListener(this);

        recyclerViewAdapter = new RecyclerViewAdapter(this,data);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        recyclerView.addItemDecoration(new MyItemDecoration());
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                Toast.makeText(MainActivity.this,"position="+position,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,Brvah.class));
            }
        });
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_lv:
                recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                break;
            case R.id.btn_gv:
                recyclerView.setLayoutManager(new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false));
                break;
            case R.id.btn_stagger:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                break;
            case R.id.btn_add:
                recyclerViewAdapter.add(1);
//                recyclerView.smoothScrollToPosition(20);//RecyclerView滑动到指定位置
                break;
            case R.id.btn_delete:
                recyclerViewAdapter.delete(1);
                break;
            default:
                break;
        }
    }
}
