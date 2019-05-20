package com.example.kudaki.profile;

import com.example.kudaki.BasePresenter;
import com.example.kudaki.BaseView;

public interface PemilikContract {
    interface View extends BaseView<PemilikContract.Presenter> {
        void showDialog();
    }

    interface Presenter extends BasePresenter {

    }
}
