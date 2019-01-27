package com.example.kudaki.login;

import com.example.kudaki.BasePresenter;
import com.example.kudaki.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void showOnLoginSuccess(String message);
    }

    interface Presenter extends BasePresenter {
        void doLogin(String email, String Password);
    }
}
