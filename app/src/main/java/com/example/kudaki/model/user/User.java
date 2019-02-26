package com.example.kudaki.model.user;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("fullname")
    String fullname;
    @SerializedName("title")
    String title;
    @SerializedName("body")
    String body;
    @SerializedName("phone")
    String phone;

    // constructor for creating user
    public User(String fullname, String email, String password, String phone) {
        this.fullname = fullname;
        this.title = email;
        this.body = password;
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
