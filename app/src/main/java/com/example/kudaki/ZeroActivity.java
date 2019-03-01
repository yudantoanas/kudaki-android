package com.example.kudaki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kudaki.login.LoginActivity;

public class ZeroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = getSharedPreferences("first", 0);

        if (sp.getBoolean("isFirst", true)){
            Intent splash = new Intent(this, SplashActivity.class);
            startActivity(splash);
            finish();
        }

    }
}
