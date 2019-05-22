package com.example.kudaki.forgotpwd;

import com.example.kudaki.model.response.SuccessResponse;
import com.example.kudaki.model.user.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPwdPresenter implements ForgotPwdContract.Presenter {
    private ForgotPwdContract.View forgotPwdView;

    public ForgotPwdPresenter(ForgotPwdContract.View loginView) {
        this.forgotPwdView = loginView;
        this.forgotPwdView.setPresenter(this);
    }

    @Override
    public void onBackClicked() {
        // confirm exit dialog / double tap to exit
    }

    @Override
    public void doSendEmail(String email) {
        User user = new User();
        user.setEmail(email);

        user.sendEmail().enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

            }

            @Override
            public void onFailure(Call<SuccessResponse> call, Throwable t) {

            }
        });
    }
}
