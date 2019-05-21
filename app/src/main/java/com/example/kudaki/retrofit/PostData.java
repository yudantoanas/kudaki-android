package com.example.kudaki.retrofit;

import com.example.kudaki.model.response.SuccessResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostData {
    @POST("/login")
    Call<SuccessResponse> loginUser(@Body RequestBody user);

    @POST("/signup")
    Call<SuccessResponse> registerUser(@Body RequestBody user);

//    // forgot password
//    @POST("/forgot-password")
//    Call<User> forgotUser();
}
