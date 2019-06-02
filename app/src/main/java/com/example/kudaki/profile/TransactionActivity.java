package com.example.kudaki.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.kudaki.R;
import com.example.kudaki.adapter.TabAdapter;
import com.example.kudaki.profile.transaction.CancelledFragment;
import com.example.kudaki.profile.transaction.DoneFragment;
import com.example.kudaki.profile.transaction.OngoingFragment;
import com.example.kudaki.profile.transaction.ProcessedFragment;
import com.example.kudaki.profile.transaction.WaitFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionActivity extends AppCompatActivity {

    @BindView(R.id.transactionTab)
    TabLayout tabLayout;
    @BindView(R.id.transactionViewPager)
    ViewPager viewPager;

    TabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        ButterKnife.bind(this);

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new WaitFragment(), "Menunggu Konfirmasi");
        adapter.addFragment(new ProcessedFragment(), "Sedang Diproses");
        adapter.addFragment(new OngoingFragment(), "Sedang Menyewa");
        adapter.addFragment(new DoneFragment(), "Selesai");
        adapter.addFragment(new CancelledFragment(), "Dibatalkan");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        Intent intent = getIntent();
        viewPager.setCurrentItem(intent.getIntExtra("fragmentItem", 0));
    }
}
