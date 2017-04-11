package com.Whiz.vaishali.deSpa;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Facebookpage_activity extends AppCompatActivity {

    WebView mwebview;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebookpage_activity);
        getSupportActionBar().hide();

        mwebview = (WebView) findViewById(R.id.webViewfacebook);
        mwebview.getSettings().setJavaScriptEnabled(true);
        mwebview.setWebViewClient(new MyWebViewClient());
        mwebview.loadUrl("https://www.facebook.com/despastudiojammu/");

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }
}