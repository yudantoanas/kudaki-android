package com.example.kudaki.renting;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.kudaki.model.response.AllItemData;
import com.example.kudaki.model.response.AllItemResponse;
import com.example.kudaki.retrofit.GetData;
import com.example.kudaki.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RentalPresenter implements RentalContract.Presenter {
    RentalContract.View view;
    Context context;

    public RentalPresenter(RentalContract.View view, Context context) {
        this.context = context;
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadItems() {
        view.showProgress();
        GetData service = RetrofitClient.getRetrofit().create(GetData.class);
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Call<AllItemResponse> call = service.getAllItems(token, 0, 10);

        call.enqueue(new Callback<AllItemResponse>() {
            @Override
            public void onResponse(Call<AllItemResponse> call, Response<AllItemResponse> response) {
                AllItemResponse resp = response.body();

                AllItemData data = resp.getData();
                view.displayItems(data);
                view.closeProgress();
            }

            @Override
            public void onFailure(Call<AllItemResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void start() {

    }
}
