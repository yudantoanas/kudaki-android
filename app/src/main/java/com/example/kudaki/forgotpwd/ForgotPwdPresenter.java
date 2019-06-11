package com.example.kudaki.forgotpwd;

import com.example.kudaki.model.response.Data;
import com.example.kudaki.model.response.ErrorResponse;
import com.example.kudaki.model.response.SuccessResponse;
import com.example.kudaki.model.user.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

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
                if (response.body() != null) {
                    SuccessResponse resp = response.body();

                    Data data = resp.getData();
                    forgotPwdView.showSendSuccess("Berhasil terkirim! Silahkan cek email Anda.");
                } else {
                    Gson gson = new GsonBuilder().create();
                    ErrorResponse errors;
                    try {
                        errors = gson.fromJson(response.errorBody().string(), ErrorResponse.class);
                        forgotPwdView.showSendFailed("Gagal mengirim email! " + errors.getErrors().get(0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SuccessResponse> call, Throwable t) {

            }
        });
    }
}
