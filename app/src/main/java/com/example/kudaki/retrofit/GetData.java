package com.example.kudaki.retrofit;

import com.example.kudaki.model.response.SuccessResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetData {
    //    @GET("/users")
//    Call<List<User>> getUsers();
    @GET("/popular")
    Call<SuccessResponse> getPopular();
}
