package com.example.kudaki;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kudaki.login.LoginActivity;
import com.orhanobut.hawk.Hawk;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Hawk.init(this).build();

        String token = Hawk.get("token");

        if (token == null) {
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
