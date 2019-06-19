package com.example.kudaki;

import com.example.kudaki.adapter.PopularAdapter;
import com.example.kudaki.model.mountain.Mountain;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mainView;

    public MainPresenter(MainContract.View loginView) {
        this.mainView = loginView;
        this.mainView.setPresenter(this);
    }

    @Override
    public void loadMountain(List<Mountain> mountainList, PopularAdapter popularAdapter) {
        mountainList.clear();
        mountainList.add(new Mountain(
                1,
                "Gunung Rinjani",
                "https://cdn2.tstatic.net/kaltim/foto/bank/images/gunung-rinjani_20180908_102029.jpg",
                "Mount Rinjani, Indonesia",
                3.726,
                -8.411295,
                116.4485726
        ));
        mountainList.add(new Mountain(
                2,
                "Gunung Merbabu",
                "http://cdn2.tstatic.net/bangka/foto/bank/images/gunung-merbabu.jpg",
                "Mount Merbabu, Indonesia",
                3.726,
                -8.411295,
                116.4485726
        ));
        popularAdapter.notifyDataSetChanged();

        /*Mountain mountain = new Mountain();
        mountain.getPopular();*/
    }

    @Override
    public void start() {

    }
}
