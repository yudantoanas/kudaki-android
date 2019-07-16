package com.example.kudaki.transaction;

import com.example.kudaki.BasePresenter;
import com.example.kudaki.BaseView;
import com.example.kudaki.model.response.OrderHistoryData;

public interface TransactionContract {
    interface View extends BaseView<Presenter> {
        void showProgress();

        void closeProgress();

        void display(OrderHistoryData data);

    }

    interface Presenter extends BasePresenter {
        void loadTransaction();
    }
}
