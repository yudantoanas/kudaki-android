package com.example.kudaki.model.equipment;

public class Equipment {
    String uuid;
    String storeUuid;
    String name;
    String desc;
    String photo;
    int price;
    Double rating;
    String duration;

    public Equipment(String uuid, String storeUuid, String name, String desc, String photo, int price, Double rating, String duration) {
        this.uuid = uuid;
        this.storeUuid = storeUuid;
        this.name = name;
        this.desc = desc;
        this.photo = photo;
        this.price = price;
        this.rating = rating;
        this.duration = duration;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStoreUuid() {
        return storeUuid;
    }

    public void setStoreUuid(String storeUuid) {
        this.storeUuid = storeUuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
