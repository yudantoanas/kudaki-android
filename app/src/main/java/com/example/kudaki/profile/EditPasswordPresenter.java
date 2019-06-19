package com.example.kudaki.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import com.example.kudaki.model.response.SuccessResponse;
import com.example.kudaki.retrofit.PostData;
import com.example.kudaki.retrofit.RetrofitClient;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPasswordPresenter implements EditPasswordContract.Presenter {
    Context context;
    EditPasswordContract.View view;

    public EditPasswordPresenter(Context context, EditPasswordContract.View view) {
        this.context = context;
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void changePassword(EditText oldPwd, EditText newPwd) {
        view.showProgress();

        if (newPwd.getText().toString().length() < 8) {
            view.closeProgress();
            view.showChangeFailed("Gagal daftar! Password Anda kurang dari 8 karakter");
        } else if (!newPwd.getText().toString().matches("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]+$")) {
            view.closeProgress();
            view.showChangeFailed("Gagal daftar! Password Anda minimal harus memilik 1 angka dan 1 huruf");
        } else {
            PostData service = RetrofitClient.getRetrofit().create(PostData.class);
            SharedPreferences sharedPreferences = context.getSharedPreferences("LoginToken", Context.MODE_PRIVATE);
            String token = sharedPreferences.getString("token", "");
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("old_password", oldPwd.getText().toString())
                    .addFormDataPart("new_password", newPwd.getText().toString())
                    .build();

            Call<SuccessResponse> call = service.changePwd(token, requestBody);

            call.enqueue(new Callback<SuccessResponse>() {
                @Override
                public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                    if (response.body() != null) {
                        view.closeProgress();
                        view.showChangeSuccess("Berhasil Ubah Password");
                    } else if (response.errorBody() != null) {
                        view.closeProgress();
                        view.showChangeFailed("Password lama salah");
                    }
                }

                @Override
                public void onFailure(Call<SuccessResponse> call, Throwable t) {

                }
            });
        }
    }
}
