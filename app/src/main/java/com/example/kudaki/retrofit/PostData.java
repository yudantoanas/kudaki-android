package com.example.kudaki.retrofit;

import com.example.kudaki.model.response.SuccessResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface PostData {
    @POST("/login")
    Call<SuccessResponse> loginUser(@Body RequestBody user);

    @POST("/signup")
    Call<SuccessResponse> registerUser(@Body RequestBody user);

    @POST("/user/password/reset")
    Call<SuccessResponse> sendForgotPwdEmail(@Body RequestBody user);

    @PUT("/user/password/change")
    Call<SuccessResponse> changePwd(@Header ("Kudaki-Token") String token,
                                    @Body RequestBody user);

    @PUT("/user/password/reset")
    Call<SuccessResponse> resetPwd(@Query("reset_token") String token,
                                    @Body RequestBody user);

    @POST("/rental/cart/item")
    Call<SuccessResponse> addToCart(@Header ("Kudaki-Token") String token,
                                    @Body RequestBody user
    );
}
