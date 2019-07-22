package com.example.kudaki;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kudaki.adapter.PopularAdapter;
import com.example.kudaki.event.EventActivity;
import com.example.kudaki.explore.ExploreActivity;
import com.example.kudaki.model.response.Mountain;
import com.example.kudaki.model.response.MountainData;
import com.example.kudaki.profile.ProfileActivity;
import com.example.kudaki.renting.RentalActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.orhanobut.hawk.Hawk;
import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    @BindView(R.id.homeNav)
    BottomNavigationView bottomNav;
    @BindView(R.id.rvPopular)
    RecyclerView recyclerView;
    @BindView(R.id.homeToolbar)
    Toolbar toolbar;
    @BindView(R.id.carousel)
    CarouselView carouselView;

    String token;

    MainPresenter mainPresenter;
    MainContract.Presenter contractPresenter;

    ArrayList<Mountain> list;
    PopularAdapter adapter;

    int[] sampleImages = {R.drawable.event_dummy_1, R.drawable.event_dummy_2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        Hawk.init(this).build();

        token = Hawk.get("token");

        mainPresenter = new MainPresenter(this, token);

        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener((position, imageView) -> imageView.setImageResource(sampleImages[position]));

        list = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        contractPresenter.loadPopular();
    }

    @Override
    protected void onResume() {
        super.onResume();

        bottomNav.getMenu().getItem(0).setChecked(true);
        bottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navHome:
                    return true;
                case R.id.navEvent:
                    startActivity(new Intent(this, EventActivity.class));
                    finish();
                    return true;
                case R.id.navExplore:
                    startActivity(new Intent(this, ExploreActivity.class));
                    finish();
                    return true;
                case R.id.navRental:
                    startActivity(new Intent(this, RentalActivity.class));
                    finish();
                    return true;
                case R.id.navProfile:
                    startActivity(new Intent(this, ProfileActivity.class));
                    finish();
                    return true;
            }
            return false;
        });
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.contractPresenter = presenter;
    }

    @Override
    public void showPopularData(MountainData data) {
        if (data.getMountains() == null) {

        } else {
            list.clear();
            for (int i = 0; i < data.getMountains().size(); i++) {
                list.add(new Mountain(
                        data.getMountains().get(i).getName(),
                        data.getMountains().get(i).getHeight(),
                        data.getMountains().get(i).getLatitude(),
                        data.getMountains().get(i).getLongitude(),
                        data.getMountains().get(i).getDifficulty(),
                        data.getMountains().get(i).getDescription()
                ));
            }
            adapter = new PopularAdapter(this, list);
            adapter.notifyDataSetChanged();
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
            recyclerView.setAdapter(adapter);
        }
    }
}
