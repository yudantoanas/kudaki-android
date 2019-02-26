package com.example.kudaki.login;

import android.util.Log;

import com.example.kudaki.model.user.User;
import com.example.kudaki.retrofit.PostData;
import com.example.kudaki.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        PostData service = RetrofitClient.getRetrofit().create(PostData.class);
        Call<User> call = service.loginUser();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("LOGIN", "onResponse: " + response.body());
                String message = "email : "+ email + " & password : " + password;
                loginView.showOnLoginSuccess(message);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loginView.showOnLoginFailed("failed login");
            }
        });
    }
}
