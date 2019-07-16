package com.example.kudaki.profile;

import com.example.kudaki.model.response.AddressData;
import com.example.kudaki.model.response.AddressResponse;
import com.example.kudaki.model.response.DefaultResponse;
import com.example.kudaki.model.response.ProfileData;
import com.example.kudaki.model.response.ProfileResponse;
import com.example.kudaki.retrofit.GetData;
import com.example.kudaki.retrofit.PostData;
import com.example.kudaki.retrofit.RetrofitClient;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter implements ProfileContract.Presenter {
    String token;

    ProfileContract.View view;

    public ProfilePresenter(ProfileContract.View view, String token) {
        this.token = token;
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadProfile() {
        view.showProgress();
        GetData service = RetrofitClient.getRetrofit().create(GetData.class);
        Call<ProfileResponse> call = service.getProfile(token);

        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.body() != null) {
                    ProfileResponse resp = response.body();

                    ProfileData data = resp.getData(); // simpan data.getToken di cache
                    view.display(data);
                    view.closeProgress();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAddress() {
        GetData service = RetrofitClient.getRetrofit().create(GetData.class);
        Call<AddressResponse> call = service.getAddress(token);

        call.enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                AddressResponse resp = response.body();

                AddressData data = resp.getData();
                view.checkAddress(data);
            }

            @Override
            public void onFailure(Call<AddressResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void saveAddress(String address) {
        view.showProgress();
        PostData service = RetrofitClient.getRetrofit().create(PostData.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("receiver_name", "")
                .addFormDataPart("full_address", address)
                .addFormDataPart("receiver_phone_number", "")
                .addFormDataPart("latitude", "")
                .addFormDataPart("longitude", "")
                .addFormDataPart("zip_code", "")
                .build();
        Call<DefaultResponse> call = service.addAddress(token, requestBody);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 200) {
                    view.showBeginSuccess();
                    view.closeProgress();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void start() {

    }
}
