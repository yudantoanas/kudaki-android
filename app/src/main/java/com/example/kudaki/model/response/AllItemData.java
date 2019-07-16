package com.example.kudaki.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllItemData {
    @SerializedName("items")
    @Expose
    private List<StoreItem> items = null;

    public List<StoreItem> getItems() {
        return items;
    }

    public void setItems(List<StoreItem> items) {
        this.items = items;
    }
}
