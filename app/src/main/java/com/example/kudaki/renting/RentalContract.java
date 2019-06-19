package com.example.kudaki.renting;

import com.example.kudaki.BasePresenter;
import com.example.kudaki.BaseView;
import com.example.kudaki.model.response.RentalResponse;

public class RentalContract {
    interface View extends BaseView<Presenter> {
        void displayItems(RentalResponse.RentalData data);
    }

    interface Presenter extends BasePresenter {
        void loadItems();
    }
}
