package com.example.kudaki.forgotpwd;

import com.example.kudaki.model.response.ErrorResponse;
import com.example.kudaki.model.response.SuccessResponse;
import com.example.kudaki.retrofit.PostData;
import com.example.kudaki.retrofit.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPwdPresenter implements ForgotPwdContract.Presenter {
    private ForgotPwdContract.View view;

    public ForgotPwdPresenter(ForgotPwdContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void doSendEmail(String email) {
        view.showProgress();

        // retrofit add to cart
        PostData service = RetrofitClient.getRetrofit().create(PostData.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", email)
                .build();
        Call<SuccessResponse> call = service.sendForgotPwdEmail(requestBody);
        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                view.closeProgress();
                if (response.body() != null) {
                    view.showSendSuccess("Berhasil terkirim! Silahkan cek email Anda.");
                } else {
                    Gson gson = new GsonBuilder().create();
                    ErrorResponse errors;
                    try {
                        errors = gson.fromJson(response.errorBody().string(), ErrorResponse.class);
                        view.showSendFailed("Gagal mengirim email! " + errors.getErrors().get(0));
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

    @Override
    public void start() {

    }
}
