package com.test.webview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
{

    private static final String URL = "http://www.jianshu.com/p/c51174efd824";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.wv_test);
        //获得webview的设置，并设置webview支持js
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.addJavascriptInterface(new scriptInterface(MainActivity.this), "imagelistner");
        webView.loadUrl("");
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
