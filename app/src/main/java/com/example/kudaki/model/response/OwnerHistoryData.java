package com.example.kudaki.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OwnerHistoryData {
    @SerializedName("orders")
    @Expose
    private List<OrderOwner> orders = null;

    public List<OrderOwner> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderOwner> orders) {
        this.orders = orders;
    }
}
