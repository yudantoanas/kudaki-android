package com.example.kudaki.renting;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kudaki.MainActivity;
import com.example.kudaki.R;
import com.example.kudaki.adapter.EquipmentAdapter;
import com.example.kudaki.cart.CartActivity;
import com.example.kudaki.event.EventActivity;
import com.example.kudaki.explore.ExploreActivity;
import com.example.kudaki.model.equipment.Equipment;
import com.example.kudaki.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RentalActivity extends AppCompatActivity {
    @BindView(R.id.rentalNav)
    BottomNavigationView bottomNav;
    @BindView(R.id.rvEquipment)
    RecyclerView recyclerView;
    private List<Equipment> list;
    private EquipmentAdapter adapter;

    // dummy mountain loader
    private void loadEquipment() {
        list.clear();
        list.add(new Equipment(
                "https://images.unsplash.com/photo-1506255677943-8d8cb3619c10?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=749&q=80",
                "Tas Carrier Eqier",
                "20000",
                "Ardi"
        ));

        list.add(new Equipment(
                "https://images.unsplash.com/photo-1506255677943-8d8cb3619c10?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=749&q=80",
                "Tas Carrier Endover",
                "90000",
                "Ardi"
        ));

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.renting_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.shopping_cart:
                startActivity(new Intent(this, CartActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        ButterKnife.bind(this);

        list = new ArrayList<>();
        adapter = new EquipmentAdapter(this, list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        loadEquipment();
    }

    @Override
    protected void onResume() {
        super.onResume();

        bottomNav.getMenu().getItem(3).setChecked(true);
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
                    startActivity(new Intent(this, ExploreActivity.class));
                    finish();
                    return true;
                case R.id.navRental:
                    return true;
                case R.id.navProfile:
                    startActivity(new Intent(this, ProfileActivity.class));
                    finish();
                    return true;
            }
            return false;
        });
    }
}
