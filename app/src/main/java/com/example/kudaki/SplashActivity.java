package com.example.kudaki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kudaki.login.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*// check if already login
        SharedPreferences loginStatus = getSharedPreferences("loginStatus", MODE_PRIVATE);
        boolean isLogin = loginStatus.getBoolean("isLogin", false);
        // islogin = true, go to base activity
        if (isLogin) {
            Intent home = new Intent(this, MainActivity.class);
            startActivity(home);
            finish();
        } else {
            Intent login = new Intent(this, LoginActivity.class);
            startActivityForResult(login, 1);
            finish();
        }*/

        Intent login = new Intent(this, LoginActivity.class);
        startActivityForResult(login, 1);
        finish();
    }
}
