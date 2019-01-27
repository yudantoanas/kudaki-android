package com.example.kudaki.login;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View loginView;

    public LoginPresenter(LoginContract.View loginView) {
        this.loginView = loginView;
        this.loginView.setPresenter(this);
    }

    @Override
    public void start() {
        // check user if already logged in
    }

    @Override
    public void doLogin(String email, String password) {
        String message = "email : "+ email + " & password : " + password;
        loginView.showOnLoginSuccess(message);
    }
}
