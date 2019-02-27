package com.example.kudaki.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kudaki.R;
import com.example.kudaki.model.user.User;
import com.example.kudaki.register.RegisterActivity;
import com.example.kudaki.register.RegisterContract;
import com.example.kudaki.retrofit.PostData;
import com.example.kudaki.retrofit.RetrofitClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

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
        button.setOnClickListener(v -> contractPresenter
                .doLogin(email.getText().toString(), password.getText().toString()));

        linkSignup.setOnClickListener(v -> {
            Intent register = new Intent(v.getContext(), RegisterActivity.class);
            // start register activity that will return a result.
            startActivityForResult(register, 1);
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
    }

    @Override
    public void showOnLoginFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
