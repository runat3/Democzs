package com.test.retrofit.apiserver;

import com.test.retrofit.model.BookInfo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/11/28.
 */

public interface APIServer
{
    @GET("v2/book/{bookId}")
    Call<BookInfo> getBookInfo(@Path("bookId") String bookId);
}
