package com.example.kudaki.retrofit;

import com.example.kudaki.model.response.RentalResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface GetData {
    //    @GET("/users")
//    Call<List<User>> getUsers();
    @GET("/store/items")
    Call<RentalResponse> getAllItems(@Header ("Kudaki-Token") String token,
                                     @Query("offset") int offset,
                                     @Query("limit") int limit);
}
