package com.example.kudaki.reset;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.example.kudaki.R;

public class ResetActivity extends AppCompatActivity {
    Intent intent;
    Uri data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        intent = getIntent();
        data = intent.getData();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // get reset_token value
        // data.getQueryParameter("reset_token")
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }
}
