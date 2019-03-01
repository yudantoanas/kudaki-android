package com.example.kudaki.search;

public class SearchPresenter implements SearchContract.Presenter {

    SearchContract.View equipmentView;

    public SearchPresenter(SearchContract.View equipmentView) {
        this.equipmentView = equipmentView;
        equipmentView.setPresenter(this);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void searchData(String query) {
        // begin search data through database
        // .....

        equipmentView.onSearchSuccess();
    }

    @Override
    public void onBackClicked() {

    }
}
