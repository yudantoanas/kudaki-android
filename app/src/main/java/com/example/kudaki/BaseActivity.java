package com.example.kudaki;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.kudaki.search.SearchFragment;
import com.example.kudaki.search.SearchPresenter;
import com.example.kudaki.booking.BookingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.bottomNav) BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        ButterKnife.bind(this);

        toolbar.setTitle("Kudaki");
        setSupportActionBar(toolbar);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new HomeFragment())
                .commit();

        // bottom nav
        bottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            // swtich to another fragment when clicked
            switch (menuItem.getItemId()){
                case R.id.navigation_home:
                    toolbar.setTitle("Kudaki");
                    HomeFragment homeFragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, homeFragment)
                            .commit();
                    // new HomePresenter();
                    break;
                case R.id.navigation_search:
                    toolbar.setTitle("Search");
                    SearchFragment searchFragment = new SearchFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, searchFragment)
                            .commit();

                    new SearchPresenter(searchFragment);
                    break;
                case R.id.navigation_booking:
                    toolbar.setTitle("Equipment");
                    BookingFragment bookingFragment = new BookingFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, bookingFragment)
                            .commit();

                    // new SearchPresenter();
                    break;
                case R.id.navigation_profile:
                    toolbar.setTitle("Preparation");
                    ProfileFragment profileFragment = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, profileFragment)
                            .commit();

                    // new PreparationPresenter();
                    break;
            }
            return true;
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // destroy loginStatus preference
        SharedPreferences loginStatus = getSharedPreferences("loginStatus", MODE_PRIVATE);
        SharedPreferences.Editor editor = loginStatus.edit();
        editor.remove("isLogin");
        editor.apply();
    }
}
