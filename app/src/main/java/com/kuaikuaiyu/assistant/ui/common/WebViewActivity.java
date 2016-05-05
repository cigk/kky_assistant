package com.kuaikuaiyu.assistant.ui.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2015/9/21
 * Desc:    h5页面统一入口
 */
public class WebViewActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.ib_back)
    ImageButton ib_back;
    @Bind(R.id.webview)
    WebView mWebView;

    private String url;
    private String title;

    @Override
    protected void initComponent() {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
    }

    @Override
    protected int getRootView() {
        return R.layout.activity_webview;
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText(title);
        mWebView.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= 21)
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        mWebView.setWebViewClient(new MyWebClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.addJavascriptInterface(new JavaScriptInterface(this), "interface");
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebView.loadUrl(url);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    /**
     * 回退
     */
    @OnClick(R.id.ib_back)
    public void back() {
        onBackPressed();
    }

    /**
     * WebviewClient
     */
    private class MyWebClient extends WebViewClient {

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            showLoading();
        }

        public void onPageFinished(WebView view, String url) {
            hideLoading();
        }

        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();  // 接受所有网站的证书
        }


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!url.toLowerCase().startsWith("http") && !url.toLowerCase().startsWith("https")) {
                try {
                    // 以下固定写法
                    final Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(url));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    WebViewActivity.this.startActivity(intent);

                } catch (Exception e) {
                    // 防止没有安装的情况
                    e.printStackTrace();
                }
                return true;
            }
            return url.toLowerCase().contains("downloadanroid") || url.toLowerCase().contains("app-download");
        }
    }

    /**
     * ChromeClient
     */
    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivity.this);
            builder.setTitle("提示")
                    .setMessage(message)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            result.confirm();
                            dialog.dismiss();
                        }
                    })
                    .setCancelable(false)
                    .show();
            return true;
        }

    }

    /**
     * 加载指定url的静态方法
     *
     * @param activity
     * @param url      指定url
     */
    public static void start(Activity activity, String url, String title) {
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        activity.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null)
            mWebView.destroy();
        super.onDestroy();
    }
}
