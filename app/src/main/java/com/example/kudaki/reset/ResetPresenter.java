package com.example.kudaki.reset;

import android.content.Context;

import com.example.kudaki.model.response.SuccessResponse;
import com.example.kudaki.retrofit.PostData;
import com.example.kudaki.retrofit.RetrofitClient;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPresenter implements ResetContract.Presenter {
    ResetContract.View view;
    Context context;

    public ResetPresenter(ResetContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void doReset(String token, String newPwd) {
        view.showProgress();

        if (newPwd.length() < 8) {
            view.closeProgress();
            view.showResetFailed("Gagal daftar! Password Anda kurang dari 8 karakter");
        } else if (!newPwd.matches("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]+$")) {
            view.closeProgress();
            view.showResetFailed("Gagal daftar! Password Anda minimal harus memilik 1 angka dan 1 huruf");
        } else {
            PostData service = RetrofitClient.getRetrofit().create(PostData.class);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("new_password", newPwd)
                    .build();
            Call<SuccessResponse> call = service.resetPwd(token, requestBody);

            call.enqueue(new Callback<SuccessResponse>() {
                @Override
                public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                    view.closeProgress();
                    if (response.body() != null){
                        view.showResetSuccess("Berhasil reset password");
                    } else if (response.errorBody() != null) {
                        view.showResetFailed("Gagal reset password");
                    }
                }

                @Override
                public void onFailure(Call<SuccessResponse> call, Throwable t) {

                }
            });
        }
    }
}
