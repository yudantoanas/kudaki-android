package com.example.kudaki.profile;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.kudaki.R;
import com.example.kudaki.adapter.ProfileTabAdapter;
import com.example.kudaki.setting.SettingActivity;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    @BindView(R.id.profileTab)
    TabLayout profileTab;
    @BindView(R.id.profileViewPager)
    ViewPager profileViewPager;

    ProfileTabAdapter adapter;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.profile_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                getActivity().startActivity(new Intent(getActivity(), SettingActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        adapter = new ProfileTabAdapter(getChildFragmentManager());
        adapter.addFragment(new PenggunaFragment(), "Pengguna");
        adapter.addFragment(new PemilikFragment(), "Pemilik");
        adapter.addFragment(new RekomendasiFragment(), "Rekomendasi");

        profileViewPager.setAdapter(adapter);
        profileTab.setupWithViewPager(profileViewPager);

        setHasOptionsMenu(true);
        return view;
    }

}
