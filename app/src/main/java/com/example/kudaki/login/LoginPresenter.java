package com.example.kudaki.login;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View loginView;

    public LoginPresenter(LoginContract.View loginView) {
        this.loginView = loginView;
        this.loginView.setPresenter(this);
    }

    @Override
    public void doLogin(String email, String password) {
        loginView.showOnLoginSuccess("Success");
        /*PostData service = RetrofitClient.getRetrofit().create(PostData.class);
        Call<User> call = service.loginUser();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                loginView.showOnLoginSuccess("Login Success");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loginView.showOnLoginFailed("Login Failed");
            }
        });*/
    }

    @Override
    public void linkSignupClicked() {
        loginView.showSignupActivity(1);
    }

    @Override
    public void onBackClicked() {
        // confirm exit dialog / double tap to exit
    }
}
