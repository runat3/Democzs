package com.test.retrofit.apiserver;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/11/28.
 */

public interface DouBanAPIServer
{
    @GET("v2/book/{bookId}")
    Call<ResponseBody> getBookInfo(@Path("bookId") String bookId);
}
