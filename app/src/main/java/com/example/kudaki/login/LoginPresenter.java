package com.example.kudaki.login;

import android.content.Intent;
import android.util.Log;

import com.example.kudaki.model.response.Data;
import com.example.kudaki.model.response.ErrorResponse;
import com.example.kudaki.model.response.LoginResponse;
import com.example.kudaki.model.user.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

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
    public void doLogin(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        loginView.showProgress();

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
    public void doGoogleLogin(Intent data) {
        // The Task returned from this call is always completed, no need to attach
        // a listener.
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            loginView.updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            loginView.updateUI(null);
        }
    }

    @Override
    public void linkSignupClicked() {
        loginView.showSignupActivity(1);
    }

    @Override
    public void linkForgotPwdClicked() {
        loginView.showForgotPwdActivity();
    }

    @Override
    public void onBackClicked() {
        // confirm exit dialog / double tap to exit
    }
}
