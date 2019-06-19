package com.example.kudaki.login;

import android.content.Intent;

import com.example.kudaki.BasePresenter;
import com.example.kudaki.BaseView;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class LoginContract {
    interface View extends BaseView<Presenter> {
        void showOnLoginSuccess(String message, String token);

        void showEmailNotExist(String email);

        void showOnLoginFailed(String message);

        void showSignupActivity(int requestCode);

        void showProgress();

        void closeProgress();

        void showForgotPwdActivity();

        void updateUI(GoogleSignInAccount account);
    }

    interface Presenter extends BasePresenter {
        void doLogin(String email, String Password);

        void doGoogleLogin(Intent data);

        void linkSignupClicked();

        void linkForgotPwdClicked();
    }
}
