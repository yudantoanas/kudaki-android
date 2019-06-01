package com.example.kudaki.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kudaki.R;
import com.example.kudaki.adapter.PopularAdapter;
import com.example.kudaki.model.mountain.Mountain;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    @BindView(R.id.rvPopular)
    RecyclerView rvPopular;

    @BindView(R.id.homeToolbar)
    Toolbar toolbar;

    private List<Mountain> mountainList;
    private PopularAdapter popularAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optNotification:
                // intent notification
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        mountainList = new ArrayList<>();
        popularAdapter = new PopularAdapter(getActivity(), mountainList);

        rvPopular.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvPopular.setAdapter(popularAdapter);
        loadPopular();

        setHasOptionsMenu(true);
        return view;
    }

    private void loadPopular() {
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

        mountainList.add(new Mountain(
                2,
                "Gunung Merbabu",
                "https://images.unsplash.com/photo-1506255677943-8d8cb3619c10?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=749&q=80",
                "Mount Merbabu, Indonesia",
                3.726,
                -8.411295,
                116.4485726
        ));

        popularAdapter.notifyDataSetChanged();
    }

}
