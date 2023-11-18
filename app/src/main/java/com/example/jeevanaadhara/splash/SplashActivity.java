package com.example.jeevanaadhara.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.jeevanaadhara.R;
import com.example.jeevanaadhara.signin.SignInActivity;

public class SplashActivity extends AppCompatActivity {
    private final int stime=4000;
    TextView textView,slogans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        textView=findViewById(R.id.textView);
        slogans=findViewById(R.id.slogan);
        slogans.animate().translationY(-400).setDuration(1000).setStartDelay(500);
        textView.animate().translationY(-400).setDuration(1000).setStartDelay(500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));

                finish();
            }

        },stime);
    }
}