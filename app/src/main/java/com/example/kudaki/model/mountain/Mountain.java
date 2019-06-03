package com.example.kudaki.model.mountain;

import com.example.kudaki.model.response.SuccessResponse;

import retrofit2.Call;

public class Mountain {
    private int id;
    private String name;
    private String photoPath;
    private String description;
    private double height;
    private double latitude;
    private double longitude;

    public Mountain(int id, String name, String photoPath, String description, double height, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.photoPath = photoPath;
        this.description = description;
        this.height = height;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Mountain() {

    }

    public Call<SuccessResponse> getPopular() {

        return null;
    }

    public Call<SuccessResponse> getAllMountain() {

        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
