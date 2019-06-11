package com.example.kudaki;

import com.example.kudaki.adapter.PopularAdapter;
import com.example.kudaki.model.mountain.Mountain;

import java.util.List;

public interface MainContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void loadMountain(List<Mountain> mountainList, PopularAdapter popularAdapter);
    }
}
