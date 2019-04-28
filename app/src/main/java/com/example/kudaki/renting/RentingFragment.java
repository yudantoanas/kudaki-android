package com.example.kudaki.renting;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kudaki.R;
import com.example.kudaki.explore.MountainAdapter;
import com.example.kudaki.model.Equipment;
import com.example.kudaki.model.mountain.Mountain;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RentingFragment extends Fragment {
    @BindView(R.id.rvEquipment) RecyclerView rvEquipment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_renting, container, false);
        ButterKnife.bind(this, view);

        equipmentList = new ArrayList<>();
        equipmentAdapter = new EquipmentAdapter(getActivity(), equipmentList);

        rvEquipment.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rvEquipment.setAdapter(equipmentAdapter);
        loadEquipment();

        setHasOptionsMenu(true);
        return view;
    }

}
