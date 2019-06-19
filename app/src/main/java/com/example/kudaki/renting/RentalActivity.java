package com.example.kudaki.renting;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kudaki.MainActivity;
import com.example.kudaki.R;
import com.example.kudaki.adapter.EquipmentAdapter;
import com.example.kudaki.cart.CartActivity;
import com.example.kudaki.event.EventActivity;
import com.example.kudaki.explore.ExploreActivity;
import com.example.kudaki.model.equipment.Equipment;
import com.example.kudaki.model.response.RentalResponse;
import com.example.kudaki.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RentalActivity extends AppCompatActivity implements RentalContract.View {
    @BindView(R.id.rentalToolbar)
    Toolbar toolbar;
    @BindView(R.id.rentalNav)
    BottomNavigationView bottomNav;
    @BindView(R.id.rvEquipment)
    RecyclerView recyclerView;

    private List<Equipment> list;
    private EquipmentAdapter adapter;

    RentalContract.Presenter contractPresenter;
    RentalPresenter presenter;

    private void loadEquipment() {

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
                Intent cart = new Intent(this, CartActivity.class);
                startActivity(cart);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        ButterKnife.bind(this);

        presenter = new RentalPresenter(this, this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list = new ArrayList<>();
        adapter = new EquipmentAdapter(this, list);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        contractPresenter.loadItems();

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

    @Override
    public void setPresenter(RentalContract.Presenter presenter) {
        this.contractPresenter = presenter;
    }

    @Override
    public void displayItems(RentalResponse.RentalData data) {
        if (data.getTotal() == 0) {
            Toast.makeText(this, "List Alat Kosong", Toast.LENGTH_SHORT).show();
        } else {
            list.clear();
            for (int x = 0; x < data.getItems().size(); x++) {
                list.add(new Equipment(
                        data.getItems().get(x).getProperties().getItemUuid(),
                        data.getItems().get(x).getProperties().getStorefrontUuid(),
                        data.getItems().get(x).getProperties().getItemName(),
                        data.getItems().get(x).getProperties().getItemDescription(),
                        data.getItems().get(x).getProperties().getItemPhoto(),
                        data.getItems().get(x).getProperties().getItemPrice(),
                        data.getItems().get(x).getProperties().getItemRating()
                ));
            }

            adapter.notifyDataSetChanged();
        }
    }
}
