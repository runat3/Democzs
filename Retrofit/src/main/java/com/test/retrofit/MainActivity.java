package com.test.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.test.retrofit.apiserver.DouBanAPIServer;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity
{

    private static final String DOU_BAN_API_HOST = "https://api.douban.com/";
    private DouBanAPIServer douBanAPIServer;
    private TextView tv_respone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_respone = (TextView) findViewById(R.id.tv_respone);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DOU_BAN_API_HOST)
                .build();
        douBanAPIServer = retrofit.create(DouBanAPIServer.class);
    }

    public void getMethod(View view)
    {
        Call<ResponseBody> call = douBanAPIServer.getBookInfo("1003078");
        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                try
                {
                    tv_respone.setText(response.body().string());
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {

            }
        });
    }
}
