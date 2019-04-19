package com.example.kudaki.model.user;

import com.example.kudaki.model.BaseObject;

public class User extends BaseObject {
    String fullname;
    String email;
    String password;
    String phone;
    String address;
    String location;
    String postalCode;

    // constructor for creating user
    public User(String fullname, String email, String password, String phone) {
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    // constructor for completing user profile
    public User(String address, String location, String postalCode) {
        this.address = address;
        this.location = location;
        this.postalCode = postalCode;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
