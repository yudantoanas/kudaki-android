package com.example.kudaki.setting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kudaki.R;
import com.example.kudaki.login.LoginActivity;
import com.example.kudaki.login.LoginContract;
import com.example.kudaki.login.LoginPresenter;
import com.example.kudaki.profile.EditPasswordActivity;
import com.example.kudaki.profile.EditProfileActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends AppCompatActivity implements SettingContract.View {
    @BindView(R.id.cardEditProfile)
    CardView editProfile;
    @BindView(R.id.cardEditPassword)
    CardView editPassword;
    @BindView(R.id.cardLogout)
    CardView logout;

    SettingPresenter settingPresenter;
    SettingContract.Presenter contractPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        // assign presenter to this activity
        settingPresenter = new SettingPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        editProfile.setOnClickListener(view ->
                startActivity(new Intent(SettingActivity.this, EditProfileActivity.class)));

        editPassword.setOnClickListener(view ->
                startActivity(new Intent(SettingActivity.this, EditPasswordActivity.class)));

        logout.setOnClickListener(view -> {
            contractPresenter.doLogout(this);
        });
    }

    @Override
    public void setPresenter(SettingContract.Presenter presenter) {
        this.contractPresenter = presenter;
    }

    @Override
    public void showLogoutSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
    }
}
