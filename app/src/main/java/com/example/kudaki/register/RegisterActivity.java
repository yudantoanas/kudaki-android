package com.example.kudaki.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kudaki.MainActivity;
import com.example.kudaki.R;
import com.example.kudaki.model.user.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {

    @BindView(R.id.registerName) EditText name;
    @BindView(R.id.registerEmail) EditText email;
    @BindView(R.id.registerPhone) EditText phone;
    @BindView(R.id.registerPassword) EditText password;
    @BindView(R.id.registerConfirm) EditText confirm;
    @BindView(R.id.submitRegister) Button button;
    @BindView(R.id.backNavigation) ImageView backNav;

    RegisterPresenter registerPresenter;
    RegisterContract.Presenter contractPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ButterKnife.bind(this);

        // assign presenter to this activity
        registerPresenter = new RegisterPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        button.setOnClickListener(view -> {
            contractPresenter.doRegister(new User("name", "email", "password", "phone"));
        });

        // navigate up to parent
        backNav.setOnClickListener(view -> contractPresenter.backToLogin());
    }

    @Override
    public void setPresenter(RegisterContract.Presenter contractPresenter) {
        this.contractPresenter = contractPresenter;
    }

    @Override
    public void showOnRegisterSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showOnRegisterFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginActivity() {
        NavUtils.navigateUpFromSameTask(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
