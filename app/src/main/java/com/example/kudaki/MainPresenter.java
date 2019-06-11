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
                "https://images.unsplash.com/photo-1506255677943-8d8cb3619c10?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=749&q=80",
                "Mount Rinjani, Indonesia",
                3.726,
                -8.411295,
                116.4485726
        ));
        mountainList.add(new Mountain(
                2,
                "Gunung Merbabu",
                "https://images.unsplash.com/photo-1506255677943-8d8cb3619c10?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=749&q=80",
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
    public void onBackClicked() {

    }
}
