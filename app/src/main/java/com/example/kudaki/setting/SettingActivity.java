package com.example.kudaki.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.kudaki.R;
import com.example.kudaki.login.LoginActivity;
import com.example.kudaki.model.SettingMenu;
import com.example.kudaki.profile.EditPasswordActivity;
import com.example.kudaki.profile.EditProfileActivity;
import com.example.kudaki.renting.EquipmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {
    @BindView(R.id.cardEditProfile) CardView editProfile;
    @BindView(R.id.cardEditPassword) CardView editPassword;
    @BindView(R.id.cardLogout) CardView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        editProfile.setOnClickListener(view ->
                startActivity(new Intent(SettingActivity.this, EditProfileActivity.class)));

        editPassword.setOnClickListener(view ->
                startActivity(new Intent(SettingActivity.this, EditPasswordActivity.class)));

        logout.setOnClickListener(view -> {
            // Logout Code
            startActivity(new Intent(SettingActivity.this, LoginActivity.class));
            finish();
        });
    }
}
