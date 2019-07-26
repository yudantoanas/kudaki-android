package com.example.kudaki.explore;

import com.example.kudaki.BasePresenter;
import com.example.kudaki.BaseView;
import com.example.kudaki.model.response.RecommendationData;

public interface RecommendationContract {
    interface View extends BaseView<Presenter> {
        void showProgress();
        void closeProgress();
        void showData(RecommendationData data);
    }
    interface Presenter extends BasePresenter {
        void loadRecommendation();
    }
}
