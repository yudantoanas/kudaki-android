package com.example.kudaki.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kudaki.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.loginEmail) EditText email;
    @BindView(R.id.loginPassword) EditText password;
    @BindView(R.id.submitLogin) Button button;

    LoginPresenter loginPresenter;
    LoginContract.Presenter contractPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);

        // assign presenter to this activity
        loginPresenter = new LoginPresenter(this);

        button.setOnClickListener(v -> contractPresenter
                .doLogin(email.getText().toString(), password.getText().toString()));
    }

    @Override
    public void setPresenter(LoginContract.Presenter contractPresenter) {
        this.contractPresenter = contractPresenter;
    }

    @Override
    public void showOnLoginSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
