package com.example.kudaki.model.equipment;

import com.example.kudaki.model.response.SuccessResponse;

import retrofit2.Call;

public class Equipment {
    String imagePath;
    String equipmentTitle;
    String equipmentPrice;
    String userName;

    public Equipment(String imagePath, String equipmentTitle, String equipmentPrice, String userName) {
        this.imagePath = imagePath;
        this.equipmentTitle = equipmentTitle;
        this.equipmentPrice = equipmentPrice;
        this.userName = userName;
    }

    public Call<SuccessResponse> getEquipment() {

        return null;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getEquipmentTitle() {
        return equipmentTitle;
    }

    public void setEquipmentTitle(String equipmentTitle) {
        this.equipmentTitle = equipmentTitle;
    }

    public String getEquipmentPrice() {
        return equipmentPrice;
    }

    public void setEquipmentPrice(String equipmentPrice) {
        this.equipmentPrice = equipmentPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
