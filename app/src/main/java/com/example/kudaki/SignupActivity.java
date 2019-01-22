package com.example.kudaki;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kudaki.presenter.auth.signup.Signup;
import com.example.kudaki.presenter.auth.signup.SignupPresenter;
import com.example.kudaki.view.SignupView;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity implements SignupView {

    SignupPresenter signupPresenter;

    @BindView(R.id.inputName) EditText inputName;
    @BindView(R.id.inputEmail) EditText inputEmail;
    @BindView(R.id.inputPassword) EditText inputPassword;
    @BindView(R.id.inputConfirmPwd) EditText inputConfirmPwd;
    @BindView(R.id.submitSignup) Button submitSignup;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        ButterKnife.bind(this);

        toolbar.setTitle("Sign Up");

        // set action bar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        signupPresenter = new Signup(this);

        // on button clicked
        submitSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupPresenter.onSignup(
                        inputName.getText().toString(),
                        inputEmail.getText().toString(),
                        inputPassword.getText().toString()
                );
            }
        });
    }

    @Override
    public void onSignupResult(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
