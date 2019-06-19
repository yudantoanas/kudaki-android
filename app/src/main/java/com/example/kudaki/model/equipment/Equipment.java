package com.example.kudaki.model.equipment;

public class Equipment {
    String uuid;
    String storeUuid;
    String name;
    String desc;
    String photo;
    String price;
    String rating;

    public Equipment(String uuid, String storeUuid, String name, String desc, String photo, String price, String rating) {
        this.uuid = uuid;
        this.storeUuid = storeUuid;
        this.name = name;
        this.desc = desc;
        this.photo = photo;
        this.price = price;
        this.rating = rating;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
