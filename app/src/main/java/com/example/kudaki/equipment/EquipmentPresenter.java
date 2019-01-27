package com.example.kudaki.equipment;

import android.view.View;

public class EquipmentPresenter implements EquipmentContract.Presenter {

    EquipmentContract.View equipmentView;

    public EquipmentPresenter(EquipmentContract.View equipmentView) {
        this.equipmentView = equipmentView;
        equipmentView.setPresenter(this);
    }

    @Override
    public void start() {

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
}
