package com.example.kudaki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.kudaki.fragments.HomeFragment;
import com.example.kudaki.fragments.PreparationFragment;
import com.example.kudaki.fragments.SearchFragment;
import com.example.kudaki.fragments.EquipmentFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.bottomNav) BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        ButterKnife.bind(this);

        toolbar.setTitle("Kudaki");
        setSupportActionBar(toolbar);

        // init home fragment on first start
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new HomeFragment())
                .commit();

        // bottom nav
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;

                // swtich to another fragment when clicked
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.navigation_search:
                        fragment = new SearchFragment();
                        break;
                    case R.id.navigation_equipment:
                        fragment = new EquipmentFragment();
                        break;
                    case R.id.navigation_preparation:
                        fragment = new PreparationFragment();
                        break;
                }

                // apply selected fragment to the fragment container
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, fragment)
                        .commit();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login:
                Intent login = new Intent(this, SigninActivity.class);
                startActivity(login);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}