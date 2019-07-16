package com.example.kudaki.transaction;

import com.example.kudaki.model.response.OrderHistoryData;
import com.example.kudaki.model.response.OrderHistoryResponse;
import com.example.kudaki.retrofit.GetData;
import com.example.kudaki.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingPresenter implements TransactionContract.Presenter {
    TransactionContract.View view;
    String token;

    public PendingPresenter(TransactionContract.View view, String token) {
        this.token = token;
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadTransaction() {
        view.showProgress();
        GetData service = RetrofitClient.getRetrofit().create(GetData.class);
        Call<OrderHistoryResponse> call = service.getOrderHistory(token, 15, 0, "PENDING");

        call.enqueue(new Callback<OrderHistoryResponse>() {
            @Override
            public void onResponse(Call<OrderHistoryResponse> call, Response<OrderHistoryResponse> response) {
                if (response.code() == 200) {
                    OrderHistoryResponse resp = response.body();

                    OrderHistoryData data = resp.getData();
                    view.display(data);
                }
                view.closeProgress();
            }

            @Override
            public void onFailure(Call<OrderHistoryResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void start() {

    }
}
