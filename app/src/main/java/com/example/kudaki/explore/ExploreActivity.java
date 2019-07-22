package com.example.kudaki.explore;

import android.app.ProgressDialog;
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
import com.example.kudaki.model.response.Mountain;
import com.example.kudaki.model.response.MountainData;
import com.example.kudaki.profile.ProfileActivity;
import com.example.kudaki.renting.RentalActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExploreActivity extends AppCompatActivity implements ExploreContract.View {
    @BindView(R.id.exploreNav)
    BottomNavigationView bottomNav;
    @BindView(R.id.rvMountain)
    RecyclerView recyclerView;
    @BindView(R.id.exploreToolbar)
    Toolbar toolbar;

    String token;

    ExploreContract.Presenter contractPresenter;
    ExplorePresenter presenter;

    ArrayList<Mountain> list;
    MountainAdapter adapter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        Hawk.init(this).build();

        token = Hawk.get("token");

        list = new ArrayList<>();

        progressDialog = new ProgressDialog(this);

        presenter = new ExplorePresenter(this, token);
    }

    @Override
    protected void onStart() {
        super.onStart();

        contractPresenter.loadMountain();
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

    @Override
    public void showProgress() {
        progressDialog.setMax(100);
        progressDialog.setMessage("Please wait...");
        progressDialog.setTitle("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    public void closeProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showMountainData(MountainData data) {
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
            adapter = new MountainAdapter(this, list);
            adapter.notifyDataSetChanged();

            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void setPresenter(ExploreContract.Presenter presenter) {

    }
}
