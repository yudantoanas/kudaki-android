package com.example.kudaki.profile;

import android.widget.EditText;

import com.example.kudaki.BasePresenter;
import com.example.kudaki.BaseView;

public class EditPasswordContract {
    interface View extends BaseView<Presenter> {
        void showProgress();

        void closeProgress();

        void showChangeSuccess(String message);

        void showChangeFailed(String message);
    }

    interface Presenter extends BasePresenter {
        void changePassword(EditText oldPwd, EditText newPwd);
    }
}
