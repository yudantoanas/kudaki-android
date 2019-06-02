package com.example.kudaki.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.kudaki.R;
import com.example.kudaki.adapter.TabAdapter;
import com.example.kudaki.renting.EquipmentFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity {
    @BindView(R.id.userProfileTab)
    TabLayout tabLayout;
    @BindView(R.id.userProfileViewPager)
    ViewPager viewPager;

    TabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new EquipmentFragment(), "Peralatan");
        adapter.addFragment(new RecommendFragment(), "Rekomendasi");
        adapter.addFragment(new AboutFragment(), "Tentang");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
