package com.example.kudaki.model;

import android.text.TextUtils;

public class User implements iUser {

    private String name, email, password;

    // sign up
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // sign in
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
