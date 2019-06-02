package com.example.kudaki;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kudaki.adapter.PopularAdapter;
import com.example.kudaki.event.EventActivity;
import com.example.kudaki.explore.ExploreActivity;
import com.example.kudaki.model.mountain.Mountain;
import com.example.kudaki.profile.ProfileActivity;
import com.example.kudaki.renting.RentalActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;
import java.util.List;

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

    MainPresenter mainPresenter;
    MainContract.Presenter contractPresenter;
    int backPressCount = 0;
    private List<Mountain> list;
    private PopularAdapter adapter;
    //    GoogleSignInClient mGoogleSignInClient;

    int[] sampleImages = {R.drawable.image_dummy, R.drawable.image_dummy, R.drawable.image_dummy};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mainPresenter = new MainPresenter(this);

        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener((position, imageView) -> imageView.setImageResource(sampleImages[position]));

        list = new ArrayList<>();
        adapter = new PopularAdapter(this, list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

        contractPresenter.loadMountain(list, adapter);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .requestProfile()
//                .build();

        // Build a GoogleSignInClient with the options specified by gso.
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // e.g. check if user logged in or not
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optNotification:
                // intent notification
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backPressCount++;
        if (backPressCount < 2) {
            Toast.makeText(this, "Press back button two times", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Exiting app", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        // destroy loginStatus preference
//        SharedPreferences loginStatus = getSharedPreferences("loginStatus", MODE_PRIVATE);
//        SharedPreferences.Editor editor = loginStatus.edit();
//        editor.remove("isLogin");
//        editor.apply();
    }
}
