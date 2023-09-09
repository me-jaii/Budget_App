package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.budgetapp.R;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH = 4000;
    Animation animation;
    private ImageView imageview;
    private TextView appname,dcs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        animation= AnimationUtils.loadAnimation(this, R.anim.animation);
        imageview=findViewById(R.id.imageview);
        appname=findViewById(R.id.appname);
        dcs=findViewById(R.id.dcs);

        imageview.setAnimation(animation);
        appname.setAnimation(animation);
        dcs.setAnimation(animation);

        new Handler().postDelayed(() -> {
            Intent intent=new Intent(SplashScreenActivity.this, loginActivity.class);
            startActivity(intent);
            finish();
        },SPLASH);

    }
}