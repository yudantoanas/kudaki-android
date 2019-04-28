package com.example.kudaki.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;

import com.example.kudaki.model.user.User;
import com.example.kudaki.retrofit.PostData;
import com.example.kudaki.retrofit.RetrofitClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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
        PostData service = RetrofitClient.getRetrofit().create(PostData.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", email)
                .addFormDataPart("password", password)
                .build();
        Call<User> call = service.loginUser(requestBody);

        loginView.showProgress();

        // bypass login
        loginView.showOnLoginSuccess("Berhasil login");
        loginView.closeProgress();

//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                loginView.closeProgress();
//                if (response.body() != null) {
//                    if (response.body().getSuccess()) {
//                        Log.d("LOGIN", "onResponse: berhasil login");
//                        loginView.showOnLoginSuccess("Berhasil login");
//                    }
//                } else {
//                    Log.d("LOGIN", "onResponse: gagal login");
//                    loginView.showOnLoginFailed("Gagal login");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//            }
//        });
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
