package com.example.kudaki.profile.owner;

import com.example.kudaki.BasePresenter;
import com.example.kudaki.BaseView;

public interface OwnerContract {
    interface View extends BaseView<Presenter> {
        void showProgress();
        void closeProgress();
        void showAddSuccess(String message);
    }

    interface Presenter extends BasePresenter {
        void addItem(String name, String desc, String price, String duration);
    }
}
