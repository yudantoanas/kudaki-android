package com.example.kudaki.register;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.kudaki.HomeActivity;
import com.example.kudaki.R;
import com.example.kudaki.model.user.User;

import androidx.appcompat.app.ActionBar;
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
    @BindView(R.id.backLogin) ImageView back;

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

        // confirmation field text change listener
        confirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!contractPresenter.validatePassword(
                        password.getText().toString(), confirm.getText().toString()))
                {
                    confirm.setError("Password confirmation different");
                }
            }
        });

        button.setOnClickListener(view ->
        {
            if (contractPresenter.validatePassword(
                    password.getText().toString(), confirm.getText().toString()))
            {
                contractPresenter.doRegister(new User(
                        name.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        "021500000")
                );
            }
        });

        back.setOnClickListener(view ->
        {
            NavUtils.navigateUpFromSameTask(this);
        });
    }

    @Override
    public void setPresenter(RegisterContract.Presenter contractPresenter) {
        this.contractPresenter = contractPresenter;
    }

    @Override
    public void showOnRegisterSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent home = new Intent(this, HomeActivity.class);
        startActivity(home);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showOnRegisterFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
