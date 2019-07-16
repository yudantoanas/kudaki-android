package com.example.kudaki.retrofit;

import com.example.kudaki.model.response.AddressResponse;
import com.example.kudaki.model.response.AllItemResponse;
import com.example.kudaki.model.response.CartResponse;
import com.example.kudaki.model.response.OrderHistoryResponse;
import com.example.kudaki.model.response.ProfileResponse;
import com.example.kudaki.model.response.StoreResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface GetData {
    @GET("/user-info/profile")
    Call<ProfileResponse> getProfile(@Header("Kudaki-Token") String token);

    @GET("/user-info/addresses")
    Call<AddressResponse> getAddress(@Header("Kudaki-Token") String token);

    @GET("/storefront/item")
    Call<StoreResponse> getStoreItems(@Header ("Kudaki-Token") String token,
                                      @Query("limit") int limit,
                                      @Query("offset") int offset);

    @GET("/rental/cart/items")
    Call<CartResponse> getCartItems(@Header ("Kudaki-Token") String token,
                                    @Query("offset") int offset,
                                    @Query("limit") int limit);

    @GET("/items")
    Call<AllItemResponse> getAllItems(@Header ("Kudaki-Token") String token,
                                      @Query("offset") int offset,
                                      @Query("limit") int limit);

    @GET("/item/search")
    Call<AllItemResponse> searchItems(@Header ("Kudaki-Token") String token,
                                     @Query("keyword") String keyword,
                                     @Query("offset") int offset,
                                     @Query("limit") int limit);

    @GET("/order/owner")
    Call<OrderHistoryResponse> ownerOrderHistory(@Query("limit") int limit,
                                                 @Query("offset") int offset,
                                                 @Query("order_status") String status);

    @GET("/order/tenant")
    Call<OrderHistoryResponse> getOrderHistory(@Query("limit") int limit,
                                           @Query("offset") int offset,
                                           @Query("order_status") String status);

}
