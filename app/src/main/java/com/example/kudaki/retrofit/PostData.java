package com.example.kudaki.retrofit;

import com.example.kudaki.model.user.User;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostData {
    // activity_login user
    @POST("/login")
    Call<User> loginUser(@Body RequestBody user);

    // activity_register user
    @POST("/signup")
    Call<User> registerUser(@Body RequestBody user);

//    // forgot password
//    @POST("/forgot-password")
//    Call<User> forgotUser();
}
