package com.example.kudaki.presenter.auth.login;

import com.example.kudaki.model.User;
import com.example.kudaki.view.SigninView;
import com.example.kudaki.view.SignupView;

public class Signin implements SigninPresenter {
    private SigninView signinView;

    public Signin(SigninView signinView) {
        this.signinView = signinView;
    }

    @Override
    public void onSignin(String email, String password) {
        User user = new User(email, password);
    }
}
