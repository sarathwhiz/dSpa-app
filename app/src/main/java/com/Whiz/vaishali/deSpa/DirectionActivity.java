package com.Whiz.vaishali.deSpa;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DirectionActivity extends AppCompatActivity {
    WebView mwebview;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.direction_activity);
        getSupportActionBar().hide();
        mwebview=(WebView)findViewById(R.id.webViewdirection);
        mwebview.getSettings().setJavaScriptEnabled(true);
        mwebview.setWebViewClient(new MyWebViewClient());
        mwebview.loadUrl("https://www.google.co.in/maps/place/D%C3%A9+Spa+Studio/@32.7071201,74.8656785,337m/data=!3m1!1e3!4m5!3m4!1s0x391e84a360783db9:0xf207f86d779aedf2!8m2!3d32.706896!4d74.8659869");

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            return false;
        }
    }

    @Override
    public void onBackPressed(){

        super.onBackPressed();
        Intent i;
        i = new Intent(DirectionActivity.this, TheDeSpaStudio.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();

    }

}
