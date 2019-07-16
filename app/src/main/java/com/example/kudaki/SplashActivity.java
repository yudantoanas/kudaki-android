package com.example.kudaki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kudaki.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // check if already activity_login
        SharedPreferences sharedPreferences = getSharedPreferences("LoginToken", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        if (token.isEmpty()) {
            Intent login = new Intent(this, LoginActivity.class);
            startActivityForResult(login, 1);
            finish();
        } else {
            Intent home = new Intent(this, MainActivity.class);
            startActivity(home);
            finish();
        }
    }
}
