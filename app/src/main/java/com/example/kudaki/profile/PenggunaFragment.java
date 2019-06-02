package com.example.kudaki.profile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kudaki.R;
import com.example.kudaki.adapter.ProfileMenuAdapter;
import com.example.kudaki.model.MenuProfile;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PenggunaFragment extends Fragment {
    @BindView(R.id.rvPengguna)
    RecyclerView rvPengguna;

    private List<MenuProfile> menuList;
    private ProfileMenuAdapter profileMenuAdapter;

    public PenggunaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengguna, container, false);
        ButterKnife.bind(this, view);

        menuList = new ArrayList<>();
        profileMenuAdapter = new ProfileMenuAdapter(getActivity(), menuList);

        rvPengguna.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvPengguna.setAdapter(profileMenuAdapter);
        loadMenu();

        return view;
    }

    private void loadMenu() {
        menuList.clear();
        menuList.add(new MenuProfile(R.drawable.ic_wait_black, "Menunggu Konfirmasi"));
        menuList.add(new MenuProfile(R.drawable.ic_process_black, "Sedang Diproses"));
        menuList.add(new MenuProfile(R.drawable.ic_rent_black, "Sedang Menyewa"));
        menuList.add(new MenuProfile(R.drawable.ic_done_black, "Selesai"));
        menuList.add(new MenuProfile(R.drawable.ic_cancel_black, "Dibatalkan"));

        profileMenuAdapter.notifyDataSetChanged();
    }
}
