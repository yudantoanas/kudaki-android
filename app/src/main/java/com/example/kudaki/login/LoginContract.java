package com.example.kudaki.login;

import android.content.Intent;

import com.example.kudaki.BasePresenter;
import com.example.kudaki.BaseView;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void showOnLoginSuccess(String message);

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

        CallbackManager doFacebookLogin(CallbackManager callbackManager, LoginButton loginButton);

        void linkSignupClicked();

        void linkForgotPwdClicked();
    }
}
