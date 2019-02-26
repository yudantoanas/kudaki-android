package com.example.kudaki.retrofit;

import com.example.kudaki.model.user.User;

import retrofit2.Call;
import retrofit2.http.POST;

public interface PostData {
    // login user
    @POST("/login")
    Call<User> loginUser();

    // register user
    @POST("/posts")
    Call<User> registerUser();

    // forgot password
    @POST("/forgot-password")
    Call<User> forgotUser();
}
