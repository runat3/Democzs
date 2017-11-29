package com.test.webview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.test.webview.Model.NewsInfo;
import com.test.webview.Server.ApiServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{

    private static final String DOU_BAN_API_HOST = "http://www.sj-ht.com/";
    private static final String BASE_URL = "http://www.sj-ht.com/";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.wv_test);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.addJavascriptInterface(new scriptInterface(MainActivity.this), "imagelistner");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DOU_BAN_API_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Call<NewsInfo> call = apiServer.getNews("1443");
        call.enqueue(new Callback<NewsInfo>()
        {
            @Override
            public void onResponse(Call<NewsInfo> call, Response<NewsInfo> response)
            {
                NewsInfo body = response.body();
                List<NewsInfo.NewsBean> news = body.getNews();
                NewsInfo.NewsBean newsBean = news.get(0);
                String body1 = newsBean.getBody();
                String bodys = new String(Base64.decode(body1.getBytes(), Base64.DEFAULT));
                webView.loadDataWithBaseURL(BASE_URL,bodys,"text/html", "utf-8", null);
            }

            @Override
            public void onFailure(Call<NewsInfo> call, Throwable t)
            {

            }
        });
    }

    /**
     * WebView与JS交互
     **/
    // 注入js函数监听
    @android.webkit.JavascriptInterface
    private void addImageClickListner()
    {
        // 这段js函数的功能就是，遍历所有的img几点，并添加onclick函数，在还是执行的时候调用本地接口传递url过去
        webView.loadUrl("javascript:(function(){" + "var objs = document.getElementsByTagName(\"img\"); "
                + "for(var i=0;i<objs.length;i++)  " + "{" + "    objs[i].onclick=function()  " + "    {  "
                + "        window.imagelistner.openImage(this.src);  " + "    }  " + "}" + "})()");
    }

    // js通信接口
    class scriptInterface
    {

        private Context context;

        public scriptInterface(Context context)
        {
            this.context = context;
        }

        @android.webkit.JavascriptInterface
        public void openImage(String img)
        {
            //模拟点击跳转
            Toast.makeText(MainActivity.this, "点击了图片" + img,
                    Toast.LENGTH_SHORT).show();
        }
    }

    // 监听
    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public void onPageFinished(WebView view, String url)
        {
            // html加载完成之后，添加监听图片的点击js函数
            addImageClickListner();
        }
    }
}
