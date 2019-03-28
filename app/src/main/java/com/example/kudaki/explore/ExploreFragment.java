package com.example.kudaki.explore;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kudaki.R;
import com.example.kudaki.model.mountain.Mountain;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends Fragment {
    @BindView(R.id.rvMountain) RecyclerView rvMountain;

    private List<Mountain> mountainList;
    private MountainAdapter mountainAdapter;

    public ExploreFragment() {
        // Required empty public constructor
    }

    // dummy mountain loader
    private void loadMountains() {
        mountainList.clear();
        mountainList.add(new Mountain(
                1,
                "Gunung Rinjani",
                "https://images.unsplash.com/photo-1506255677943-8d8cb3619c10?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=749&q=80",
                "Mount Rinjani, Indonesia",
                3.726,
                -8.411295,
                116.4485726
        ));

        mountainAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        ButterKnife.bind(this, view);

        mountainList = new ArrayList<>();
        mountainAdapter = new MountainAdapter(getActivity(), mountainList);

        rvMountain.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMountain.setAdapter(mountainAdapter);
        loadMountains();

        return view;
    }

}
