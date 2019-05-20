package com.example.kudaki.model.user;

import com.example.kudaki.model.response.LoginResponse;
import com.example.kudaki.retrofit.PostData;
import com.example.kudaki.retrofit.RetrofitClient;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class User {
    private String fullname, email, password, phone, photoPath, role, loginToken, loginMessage;

    public User() {
    }

    // constructor for creating user
    public User(String fullname, String email, String password, String phone) {
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = "USER";
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public void setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
    }

    public Call<LoginResponse> validateUser() {
        PostData service = RetrofitClient.getRetrofit().create(PostData.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", email)
                .addFormDataPart("password", password)
                .build();
        Call<LoginResponse> call = service.loginUser(requestBody);

        return call;
    }

    public  createUser() {

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

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
