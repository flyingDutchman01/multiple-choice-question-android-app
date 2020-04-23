package com.example.multiplechoicequestion.view.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.multiplechoicequestion.R;


public class SplashActivity extends Activity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashfile);

        handler=new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, CategoryActivity.class);
            startActivity(intent);
            finish();
        },4000);

    }
}
