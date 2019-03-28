package com.example.kudaki.login;

import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View loginView;

    public LoginPresenter(LoginContract.View loginView) {
        this.loginView = loginView;
        this.loginView.setPresenter(this);
    }

    @Override
    public void doLogin(String email, String password) {
        loginView.showOnLoginSuccess("Success");
        /*PostData service = RetrofitClient.getRetrofit().create(PostData.class);
        Call<User> call = service.loginUser();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                loginView.showOnLoginSuccess("Login Success");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loginView.showOnLoginFailed("Login Failed");
            }
        });*/
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
