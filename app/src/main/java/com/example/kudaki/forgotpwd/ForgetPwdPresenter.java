package com.example.kudaki.forgotpwd;

public class ForgetPwdPresenter implements ForgotPwdContract.Presenter {
    private ForgotPwdContract.View forgotPwdView;

    public ForgetPwdPresenter(ForgotPwdContract.View loginView) {
        this.forgotPwdView = loginView;
        this.forgotPwdView.setPresenter(this);
    }

    @Override
    public void onBackClicked() {
        // confirm exit dialog / double tap to exit
    }

    @Override
    public void doSendEmail(String email) {

    }
}
