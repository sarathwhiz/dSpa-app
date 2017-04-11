package com.Whiz.vaishali.deSpa;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class VisitUs extends AppCompatActivity {

    WebView mwebview;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.visit_us);
        getSupportActionBar().hide();
        mwebview=(WebView)findViewById(R.id.webView);
        mwebview.getSettings().setJavaScriptEnabled(true);
        mwebview.setWebViewClient(new MyWebViewClient());
        mwebview.loadUrl("http://despastudio.com/");

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            return false;
        }
    }}
