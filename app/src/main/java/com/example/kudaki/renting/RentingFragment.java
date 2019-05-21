package com.example.kudaki.renting;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.kudaki.R;
import com.example.kudaki.cart.CartActivity;
import com.example.kudaki.model.Equipment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RentingFragment extends Fragment {
    @BindView(R.id.rvEquipment)
    RecyclerView rvEquipment;
    private List<Equipment> equipmentList;
    private EquipmentAdapter equipmentAdapter;


    public RentingFragment() {
        // Required empty public constructor
    }

    // dummy mountain loader
    private void loadEquipment() {
        equipmentList.clear();
        equipmentList.add(new Equipment(
                "https://images.unsplash.com/photo-1506255677943-8d8cb3619c10?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=749&q=80",
                "Tas Carrier Eqier",
                "20000",
                "Ardi"
        ));

        equipmentList.add(new Equipment(
                "https://images.unsplash.com/photo-1506255677943-8d8cb3619c10?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=749&q=80",
                "Tas Carrier Endover",
                "90000",
                "Ardi"
        ));

        equipmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.renting_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.shopping_cart:
                startActivity(new Intent(getActivity(), CartActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_renting, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        equipmentList = new ArrayList<>();
        equipmentAdapter = new EquipmentAdapter(getActivity(), equipmentList);

        rvEquipment.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvEquipment.setAdapter(equipmentAdapter);
        loadEquipment();

        setHasOptionsMenu(true);
    }
}
