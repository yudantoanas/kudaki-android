package com.example.kudaki.retrofit;

import com.example.kudaki.model.response.LoginResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostData {
    // activity_login user
    @POST("/login")
    Call<LoginResponse> loginUser(@Body RequestBody user);
//
//    // activity_register user
//    @POST("/activity_register")
//    Call<RetroUser> registerUser();
//
//    // forgot password
//    @POST("/forgot-password")
//    Call<User> forgotUser();
}
