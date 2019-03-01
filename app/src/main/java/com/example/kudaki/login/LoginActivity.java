package com.example.kudaki.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kudaki.BaseActivity;
import com.example.kudaki.R;
import com.example.kudaki.register.RegisterActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.loginEmail) EditText email;
    @BindView(R.id.loginPassword) EditText password;
    @BindView(R.id.submitLogin) Button button;
    @BindView(R.id.linkSignup) TextView linkSignup;

    LoginPresenter loginPresenter;
    LoginContract.Presenter contractPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);

        // assign presenter to this activity
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        button.setOnClickListener(v ->
                contractPresenter.doLogin(email.getText().toString(), password.getText().toString()));

        linkSignup.setOnClickListener(v -> {
            contractPresenter.linkSignupClicked();
        });
    }

    // on activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if result is OK, finish this activity
        if (resultCode == RESULT_OK) {
            this.finish();
        }
    }

    @Override
    public void setPresenter(LoginContract.Presenter contractPresenter) {
        this.contractPresenter = contractPresenter;
    }

    @Override
    public void showOnLoginSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent home = new Intent(this, BaseActivity.class);

        // set shared preference isLogin = true
        SharedPreferences sharedPreferences = this.getSharedPreferences("loginStatus",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin", true)
                .apply(); // save preference asynchronously

        // start home activity then destroy this activity
        startActivity(home);
        finish();
    }

    @Override
    public void showOnLoginFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSignupActivity(int requestCode) {
        Intent signup = new Intent(this, RegisterActivity.class);
        startActivityForResult(signup, requestCode);
    }
}
