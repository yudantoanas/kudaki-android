package com.example.kudaki.profile;

import com.example.kudaki.BasePresenter;
import com.example.kudaki.BaseView;

public interface OwnerContract {
    interface View extends BaseView<OwnerContract.Presenter> {
        void showDialog();
    }

    interface Presenter extends BasePresenter {

    }
}
