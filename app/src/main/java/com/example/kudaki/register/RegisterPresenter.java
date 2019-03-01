package com.example.kudaki.register;

import com.example.kudaki.model.user.User;

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View registerView;

    public RegisterPresenter(RegisterContract.View registerView) {
        this.registerView = registerView;
        this.registerView.setPresenter(this);
    }

    @Override
    public void doRegister(User user) {
        registerView.showOnRegisterSuccess("Success");
        /*PostData service = RetrofitClient.getRetrofit().create(PostData.class);
        Call<RetroUser> call = service.registerUser(user);

        call.enqueue(new Callback<RetroUser>() {
            @Override
            public void onResponse(Call<RetroUser> call, Response<RetroUser> response) {
                Log.d("REGISTER", "onResponse: "+response.code());
            }

            @Override
            public void onFailure(Call<RetroUser> call, Throwable t) {
                registerView.showOnRegisterFailed("Register Failed");
            }
        });*/
    }

    @Override
    public void backToLogin() {
        registerView.showLoginActivity();
    }

    @Override
    public boolean validatePassword(String password, String confirmPassword) {
        if (password.equals(confirmPassword)){
            return true;
        }
        return false;
    }

    @Override
    public void onBackClicked() {

    }
}
