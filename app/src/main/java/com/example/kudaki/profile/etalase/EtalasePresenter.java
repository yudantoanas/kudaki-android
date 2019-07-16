package com.example.kudaki.profile.etalase;

import com.example.kudaki.model.response.DefaultResponse;
import com.example.kudaki.model.response.StoreData;
import com.example.kudaki.model.response.StoreResponse;
import com.example.kudaki.retrofit.GetData;
import com.example.kudaki.retrofit.PostData;
import com.example.kudaki.retrofit.RetrofitClient;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EtalasePresenter implements EtalaseContract.Presenter {
    String token;
    EtalaseContract.View view;

    public EtalasePresenter(EtalaseContract.View view, String token) {
        this.token = token;
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadItems() {
        view.showProgress();
        GetData service = RetrofitClient.getRetrofit().create(GetData.class);
        Call<StoreResponse> call = service.getStoreItems(token, 10, 0);

        call.enqueue(new Callback<StoreResponse>() {
            @Override
            public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                if (response.code() == 200) {
                    StoreResponse resp = response.body();

                    StoreData data = resp.getData(); // simpan data.getToken di cache
                    view.showEtalaseItem(data);
                }
                view.closeProgress();
            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void addItem(String name, String desc, String price, String duration) {
        view.showProgress();
        PostData service = RetrofitClient.getRetrofit().create(PostData.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", name)
                .addFormDataPart("amount", "1")
                .addFormDataPart("color", "black")
                .addFormDataPart("descriptiom", desc)
                .addFormDataPart("price", price)
                .addFormDataPart("unit", "pairs")
                .addFormDataPart("unit_of_measurements", "CM")
                .addFormDataPart("photo", "http://google.co.id")
                .addFormDataPart("price_duration", duration)
                .addFormDataPart("height", "5")
                .addFormDataPart("length", "5")
                .addFormDataPart("width", "5")
                .build();
        Call<DefaultResponse> call = service.addStoreItem(token, requestBody);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

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
