package com.example.kudaki.model.user;

import android.util.Log;

import com.example.kudaki.model.response.Data;
import com.example.kudaki.model.response.LoginResponse;
import com.example.kudaki.retrofit.PostData;
import com.example.kudaki.retrofit.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User {
    private String fullname, email, password, phone, photoPath, role;

    String responseMsg;

    public User() {
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // constructor for creating user
    public User(String fullname, String email, String password, String phone) {
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = "USER";
    }

    public String validateUser() {
        PostData service = RetrofitClient.getRetrofit().create(PostData.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", email)
                .addFormDataPart("password", password)
                .build();
        Call<LoginResponse> call = service.loginUser(requestBody);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body() != null) {
                    Log.d("LOGIN", "onResponse: " + response.body().toString());
                    LoginResponse resp = response.body();

                    Data data = resp.getData();
                    Log.d("LOGIN", "token: " + data.getToken());
                }

                if (response.errorBody() != null) {
                    Log.d("LOGIN", "onResponse: " + response.errorBody().toString());
                    try {
                        JSONObject error = new JSONObject(response.errorBody().string());
                        JSONArray errorsArray = error.getJSONArray("errors");
                        String status = errorsArray.getString(0);
                        Log.d("LOGIN", "onResponse: " + status);
                        responseMsg = status;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

        return responseMsg;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String getRole() {
        return role;
    }
}
