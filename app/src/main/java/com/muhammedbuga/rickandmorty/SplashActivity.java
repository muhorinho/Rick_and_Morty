package com.muhammedbuga.rickandmorty;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_FIRST_TIME = "first_time";

    private boolean isFirstTime() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean firstTime = preferences.getBoolean(KEY_FIRST_TIME, true);
        if (firstTime) {
            preferences.edit().putBoolean(KEY_FIRST_TIME, false).apply();
        }
        return firstTime;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (isFirstTime()) {
            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        }

        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }, 5000);



    }


}