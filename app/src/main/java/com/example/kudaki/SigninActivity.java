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

import com.example.kudaki.presenter.auth.login.Signin;
import com.example.kudaki.presenter.auth.login.SigninPresenter;
import com.example.kudaki.view.SigninView;

public class SigninActivity extends AppCompatActivity implements SigninView {

    SigninPresenter signinPresenter;

    @BindView(R.id.inputEmail) EditText inputEmail;
    @BindView(R.id.inputPassword) EditText inputPassword;
    @BindView(R.id.submitSignin) Button submitSignin;
    @BindView(R.id.linkSignup) TextView linkSignup;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        ButterKnife.bind(this);

        toolbar.setTitle("Sign In");

        signinPresenter = new Signin(this);

        // on link clicked
        linkSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(signup);
            }
        });

        // on button clicked
        submitSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinPresenter.onSignin(
                        inputEmail.getText().toString(),
                        inputPassword.getText().toString()
                );
            }
        });
    }

    @Override
    public void onSigninResult(String message) {

    }
}
