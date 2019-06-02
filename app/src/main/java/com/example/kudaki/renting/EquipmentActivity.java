package com.example.kudaki.renting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kudaki.R;
import com.example.kudaki.cart.CartActivity;
import com.example.kudaki.profile.UserActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EquipmentActivity extends AppCompatActivity {
    @BindView(R.id.detailEquipmentAdd)
    ImageButton btnAdd;
    @BindView(R.id.detailEquipmentRental)
    TextView rentalName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnAdd.setOnClickListener(v -> {
            Intent cart = new Intent(v.getContext(), CartActivity.class);
            startActivity(cart);
        });

        rentalName.setOnClickListener(v -> {
            Intent rental = new Intent(v.getContext(), UserActivity.class);
            startActivity(rental);
        });
    }
}
