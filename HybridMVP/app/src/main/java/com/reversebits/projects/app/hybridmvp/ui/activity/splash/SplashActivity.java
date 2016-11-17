package com.reversebits.projects.app.hybridmvp.ui.activity.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.reversebits.projects.app.hybridmvp.ui.activity.home.Home;

/**
 * Created by TapanHP on 10/25/2016.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, Home.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
