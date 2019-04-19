package com.example.kudaki.register;

import android.util.Log;

import com.example.kudaki.model.user.User;
import com.example.kudaki.retrofit.PostData;
import com.example.kudaki.retrofit.RetrofitClient;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
        PostData service = RetrofitClient.getRetrofit().create(PostData.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("full_name", user.getFullname())
                .addFormDataPart("email", user.getEmail())
                .addFormDataPart("phone_number", user.getPhone())
                .addFormDataPart("password", user.getPassword())
                .addFormDataPart("role", "USER")
                .addFormDataPart("photo", "https://i.ibb.co/0ZGvmgL/eberhard-grossgasteiger-1405471-unsplash.jpg")
                .build();
        Call<User> call = service.registerUser(requestBody);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null) {
                    if (response.body().isSuccess()) {
                        Log.d("REGISTER", "onResponse: berhasil register");
                        registerView.showOnRegisterSuccess("Berhasil mendaftar");
                    }
                } else {
                    Log.d("REGISTER", "onResponse: gagal register");
                    registerView.showOnRegisterFailed("Gagal daftar");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

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
