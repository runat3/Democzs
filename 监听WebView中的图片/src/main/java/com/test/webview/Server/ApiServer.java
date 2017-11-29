package com.test.webview.Server;



import com.test.webview.Model.NewsInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/11/29.
 */

public interface ApiServer
{
    @GET("app/news.php")
    Call<NewsInfo> getNews(@Query("id") String id);
}
