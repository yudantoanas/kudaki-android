package com.example.kudaki.retrofit;

import com.example.kudaki.model.user.User;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PostData {
    // activity_login user
    @POST("/login")
    Call<User> loginUser(@Body RequestBody user);
//
//    // activity_register user
//    @POST("/activity_register")
//    Call<RetroUser> registerUser();
//
//    // forgot password
//    @POST("/forgot-password")
//    Call<User> forgotUser();
}
