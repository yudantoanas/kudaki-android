package com.example.kudaki;

import android.content.Intent;
import android.os.Bundle;

import com.example.kudaki.login.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // check if already login (there's login cache)
        // if yes
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
        finish();
        // if not
        // ...
    }
}
