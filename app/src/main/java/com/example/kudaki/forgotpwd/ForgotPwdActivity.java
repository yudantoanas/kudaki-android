package com.example.kudaki.forgotpwd;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kudaki.R;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPwdActivity extends AppCompatActivity implements ForgotPwdContract.View {
    @BindView(R.id.btnSendEmail)
    Button btnSendEmail;
    @BindView(R.id.inForgotEmail)
    EditText forgotEmail;
    ActionBar toolbar;

    ForgotPwdPresenter forgotPwdPresenter;
    ForgotPwdContract.Presenter contractPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd);
        ButterKnife.bind(this);

        forgotPwdPresenter = new ForgotPwdPresenter(this);

        toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();

        btnSendEmail.setOnClickListener(view -> {
            String email = forgotEmail.getText().toString();
            forgotPwdPresenter.doSendEmail(email);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showSendSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        NavUtils.navigateUpFromSameTask(this);
    }

    @Override
    public void showSendFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(ForgotPwdContract.Presenter presenter) {
        this.contractPresenter = presenter;
    }
}
