package com.example.kudaki.forgotpwd;

import android.content.Intent;

import com.example.kudaki.BasePresenter;
import com.example.kudaki.BaseView;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface ForgotPwdContract {
    interface View extends BaseView<Presenter> {
        void showSendSuccess(String message);
    }

    interface Presenter extends BasePresenter {
        void doSendEmail(String email);
    }
}
