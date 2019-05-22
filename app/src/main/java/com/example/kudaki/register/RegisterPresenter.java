package com.example.kudaki.register;

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

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View registerView;

    public RegisterPresenter(RegisterContract.View registerView) {
        this.registerView = registerView;
        this.registerView.setPresenter(this);
    }

    @Override
    public void doRegister(User user) {
        //.. showing progress

        // validate password
        if (!user.validatePasswordLength()) {
            registerView.showOnRegisterFailed("Gagal daftar! Password Anda kurang dari 8 karakter");
        } else if (!user.validatePasswordStrength()) {
            registerView.showOnRegisterFailed("Gagal daftar! Password Anda minimal harus memilik 1 angka dan 1 huruf");
        } else {
            // if pass, then create user
            user.createUser().enqueue(new Callback<SuccessResponse>() {
                @Override
                public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                    if (response.body() != null) {
                        SuccessResponse resp = response.body();

                        Data data = resp.getData(); // simpan data.getToken di cache
                        registerView.showOnRegisterSuccess("Berhasil daftar! Silahkan cek email untuk verifikasi.");
                    } else {
                        Gson gson = new GsonBuilder().create();
                        ErrorResponse errors;
                        try {
                            errors = gson.fromJson(response.errorBody().string(), ErrorResponse.class);
                            registerView.showOnRegisterFailed("Gagal daftar! " + errors.getErrors().get(0));
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

    @Override
    public void backToLogin() {
        registerView.showLoginActivity();
    }

    @Override
    public boolean validatePassword(String password, String confirmPassword) {
        if (password.equals(confirmPassword)) {
            return true;
        }
        return false;
    }

    @Override
    public void onBackClicked() {

    }
}
