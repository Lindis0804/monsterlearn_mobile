package com.ldh.monsterlearn.View.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.ldh.monsterlearn.R;
import com.ldh.monsterlearn.View.Log.MainActivity;

public class SplashScreen extends AppCompatActivity {
    private TextView appName;
    private LottieAnimationView lottie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        appName = findViewById(R.id.splash_screen_app_name);
        lottie = findViewById(R.id.splash_screen_lottie);
        appName.animate().translationY(-6000).setDuration(2700).setStartDelay(2900);
        lottie.animate().translationX(2000).setDuration(2000).setStartDelay(2900);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        },5000);
    }
}