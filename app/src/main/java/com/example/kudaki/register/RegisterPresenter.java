package com.example.kudaki.register;

import com.example.kudaki.model.user.User;

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View registerView;

    public RegisterPresenter(RegisterContract.View registerView) {
        this.registerView = registerView;
        this.registerView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void doRegister(User user) {
        // sends user object via POST request
        // ....

        registerView.showOnRegisterSuccess("Register Success");
    }
}
