package com.example.kudaki.equipment;

import com.example.kudaki.BasePresenter;
import com.example.kudaki.BaseView;

public interface EquipmentContract {
    interface View extends BaseView<Presenter> {
        void onSearchSuccess();
    }

    interface Presenter extends BasePresenter {
        void loadData();
        void searchData(String query);
    }
}
