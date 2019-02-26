package com.example.kudaki.register;

import android.util.Log;

import com.example.kudaki.model.user.User;
import com.example.kudaki.retrofit.PostData;
import com.example.kudaki.retrofit.RetrofitClient;

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
    public void start() {

    }

    @Override
    public void doRegister(User user) {
        PostData service = RetrofitClient.getRetrofit().create(PostData.class);
        Call<User> call = service.registerUser();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 404) {
                    registerView.showOnRegisterSuccess("Register Success");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                registerView.showOnRegisterFailed("Register Failed");
            }
        });
    }
}
