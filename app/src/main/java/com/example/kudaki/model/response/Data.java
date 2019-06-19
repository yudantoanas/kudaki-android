package com.example.kudaki.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("Total")
    @Expose
    private Integer total;
//    @SerializedName("items")
//    @Expose
//    private List<> items = null;

    public String getToken() {
        return token;
    }
}
