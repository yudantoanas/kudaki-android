package com.example.kudaki;

import androidx.fragment.app.Fragment;

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void loadFragment(Fragment fragment);
    }

    interface Presenter extends BasePresenter {

    }
}
