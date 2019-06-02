package com.example.kudaki.explore;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kudaki.MainActivity;
import com.example.kudaki.R;
import com.example.kudaki.adapter.MountainAdapter;
import com.example.kudaki.event.EventActivity;
import com.example.kudaki.model.mountain.Mountain;
import com.example.kudaki.profile.ProfileActivity;
import com.example.kudaki.renting.RentalActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExploreActivity extends AppCompatActivity {
    @BindView(R.id.exploreNav)
    BottomNavigationView bottomNav;
    @BindView(R.id.rvMountain)
    RecyclerView rvMountain;
    @BindView(R.id.exploreToolbar)
    Toolbar toolbar;

    private List<Mountain> mountainList;
    private MountainAdapter mountainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mountainList = new ArrayList<>();
        mountainAdapter = new MountainAdapter(this, mountainList);

        rvMountain.setLayoutManager(new LinearLayoutManager(this));
        rvMountain.setAdapter(mountainAdapter);
        loadMountains();
    }

    @Override
    protected void onResume() {
        super.onResume();

        bottomNav.getMenu().getItem(2).setChecked(true);
        bottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navHome:
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                    return true;
                case R.id.navEvent:
                    startActivity(new Intent(this, EventActivity.class));
                    finish();
                    return true;
                case R.id.navExplore:
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

    // dummy mountain loader
    private void loadMountains() {
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
                1,
                "Gunung Rinjani",
                "https://images.unsplash.com/photo-1506255677943-8d8cb3619c10?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=749&q=80",
                "Mount Rinjani, Indonesia",
                3.726,
                -8.411295,
                116.4485726
        ));

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
                1,
                "Gunung Rinjani",
                "https://images.unsplash.com/photo-1506255677943-8d8cb3619c10?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=749&q=80",
                "Mount Rinjani, Indonesia",
                3.726,
                -8.411295,
                116.4485726
        ));

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
                1,
                "Gunung Rinjani",
                "https://images.unsplash.com/photo-1506255677943-8d8cb3619c10?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=749&q=80",
                "Mount Rinjani, Indonesia",
                3.726,
                -8.411295,
                116.4485726
        ));

        mountainAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.explore_menu, menu);
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.optSearchMountain).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        return super.onCreateOptionsMenu(menu);
    }
}
