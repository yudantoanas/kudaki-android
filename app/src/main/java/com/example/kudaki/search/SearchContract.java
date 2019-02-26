package com.example.kudaki.search;

import com.example.kudaki.BasePresenter;
import com.example.kudaki.BaseView;

public interface SearchContract {
    interface View extends BaseView<Presenter> {
        void onSearchSuccess();
    }

    interface Presenter extends BasePresenter {
        void loadData();
        void searchData(String query);
    }
}
