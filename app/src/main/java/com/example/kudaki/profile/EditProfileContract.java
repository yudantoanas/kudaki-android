package com.example.kudaki.profile;

import com.example.kudaki.BasePresenter;
import com.example.kudaki.BaseView;

public interface EditProfileContract {
    interface View extends BaseView<Presenter>{
        void showProgress();
        void closeProgress();
        void showEditSuccess(String message, String newName, String newPhone);
        void showEditFailed(String message);
    }

    interface Presenter extends BasePresenter{
        void update(String name, String phone);
    }
}
