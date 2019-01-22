package com.example.kudaki.presenter.auth.signup;

import com.example.kudaki.model.User;
import com.example.kudaki.view.SignupView;

public class Signup implements SignupPresenter {

    private SignupView signupView;

    public Signup(SignupView signupView) {
        this.signupView = signupView;
    }

    @Override
    public void onSignup(String name, String email, String password) {
        // signupView.onSignupResult("onSignup running");
        User newUser = new User(name, email, password);
    }
}
