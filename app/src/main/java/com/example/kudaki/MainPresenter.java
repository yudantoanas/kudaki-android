package com.example.kudaki;

import com.example.kudaki.login.LoginContract;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mainView;

    public MainPresenter(MainContract.View loginView) {
        this.mainView = loginView;
        this.mainView.setPresenter(this);
    }

    @Override
    public void onBackClicked() {

    }
}
