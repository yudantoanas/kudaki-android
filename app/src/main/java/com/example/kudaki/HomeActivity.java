package com.example.kudaki;

import android.os.Bundle;

import com.example.kudaki.equipment.EquipmentFragment;
import com.example.kudaki.equipment.EquipmentPresenter;
import com.example.kudaki.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
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

                    // new SearchPresenter();
                    break;
                case R.id.navigation_equipment:
                    toolbar.setTitle("Equipment");
                    EquipmentFragment equipmentFragment = new EquipmentFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, equipmentFragment)
                            .commit();

                    new EquipmentPresenter(equipmentFragment);
                    break;
                case R.id.navigation_preparation:
                    toolbar.setTitle("Preparation");
                    PreparationFragment preparationFragment = new PreparationFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, preparationFragment)
                            .commit();

                    // new PreparationPresenter();
                    break;
            }
            return true;
        });
    }
}
