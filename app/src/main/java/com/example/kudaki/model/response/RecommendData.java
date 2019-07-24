package com.example.kudaki.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecommendData {
    @SerializedName("recommended_gears")
    @Expose
    private ArrayList<RecommendedGear> recommendedGears = null;

    public ArrayList<RecommendedGear> getRecommendedGears() {
        return recommendedGears;
    }

    public void setRecommendedGears(ArrayList<RecommendedGear> recommendedGears) {
        this.recommendedGears = recommendedGears;
    }

}
