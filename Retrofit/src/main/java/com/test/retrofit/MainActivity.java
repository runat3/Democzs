package com.test.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.test.retrofit.apiserver.APIServer;
import com.test.retrofit.model.BookInfo;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{

    private static final String DOU_BAN_API_HOST = "https://api.douban.com/";
    private APIServer APIServer;
    private TextView tv_respone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_respone = (TextView) findViewById(R.id.tv_respone);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DOU_BAN_API_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIServer = retrofit.create(APIServer.class);
    }

    public void getMethod(View view)
    {
        APIServer.getBookInfo("1003079").enqueue(new Callback<BookInfo>()
        {
            @Override
            public void onResponse(Call<BookInfo> call, Response<BookInfo> response)
            {
                Log.e("response", response.body().toString());
                tv_respone.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<BookInfo> call, Throwable t)
            {

            }
        });
    }
}
