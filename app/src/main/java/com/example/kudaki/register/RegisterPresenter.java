package com.example.kudaki.register;

import com.example.kudaki.model.response.Data;
import com.example.kudaki.model.response.ErrorResponse;
import com.example.kudaki.model.response.LoginResponse;
import com.example.kudaki.model.user.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View registerView;

    public RegisterPresenter(RegisterContract.View registerView) {
        this.registerView = registerView;
        this.registerView.setPresenter(this);
    }

    @Override
    public void doRegister(User user) {
        //.. showing progress

        user.validateUser().enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body() != null) {
                    LoginResponse resp = response.body();

                    Data data = resp.getData(); // simpan data.getToken di cache
                    loginView.showOnLoginSuccess("Login Success");
                } else {
                    Gson gson = new GsonBuilder().create();
                    ErrorResponse errors;
                    try {
                        errors = gson.fromJson(response.errorBody().string(), ErrorResponse.class);
                        loginView.showOnLoginFailed("Login Failed! "+ errors.getErrors().get(0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                loginView.closeProgress();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void backToLogin() {
        registerView.showLoginActivity();
    }

    @Override
    public boolean validatePassword(String password, String confirmPassword) {
        if (password.equals(confirmPassword)){
            return true;
        }
        return false;
    }

    @Override
    public void onBackClicked() {

    }
}
