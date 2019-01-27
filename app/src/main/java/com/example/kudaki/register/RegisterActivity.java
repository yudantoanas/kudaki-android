package com.example.kudaki.register;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kudaki.R;
import com.example.kudaki.model.user.User;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {

    @BindView(R.id.registerName) EditText name;
    @BindView(R.id.registerEmail) EditText email;
    @BindView(R.id.registerPassword) EditText password;
    @BindView(R.id.registerConfirm) EditText confirm;
    @BindView(R.id.submitRegister) Button button;

    RegisterPresenter registerPresenter;
    RegisterContract.Presenter contractPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ButterKnife.bind(this);

        // assign presenter to this activity
        registerPresenter = new RegisterPresenter(this);

        button.setOnClickListener(v -> contractPresenter
                .doRegister(
                        // passsing user object
                        new User(
                                name.getText().toString(),
                                email.getText().toString(),
                                password.getText().toString(),
                                "021500000")
                ));
    }

    @Override
    public void setPresenter(RegisterContract.Presenter contractPresenter) {
        this.contractPresenter = contractPresenter;
    }

    @Override
    public void showOnRegisterSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
