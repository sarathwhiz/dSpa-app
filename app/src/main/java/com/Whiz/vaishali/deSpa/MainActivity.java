package com.Whiz.vaishali.deSpa;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView aaa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        String fontPath = "AlexBrush-Regular.ttf";

        aaa = (TextView)findViewById(R.id.despa);
        Typeface tf = Typeface.createFromAsset(this.getAssets(), fontPath);

        aaa.setTypeface(tf);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, LoginForm.class));
                finish();
            }
        },2000);
    }


}
