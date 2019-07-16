package com.example.kudaki.transaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.viewpager.widget.ViewPager;

import com.example.kudaki.R;
import com.example.kudaki.model.response.OrderHistoryData;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionActivity extends AppCompatActivity implements TransactionContract.View {
    @BindView(R.id.transactionToolbar)
    Toolbar toolbar;
    @BindView(R.id.transactionTab)
    TabLayout tabLayout;
    @BindView(R.id.transactionViewPager)
    ViewPager viewPager;

    TabAdapter adapter;
    String token;

    TransactionContract.Presenter contractPresenter;
    TransactionPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences preferences = getSharedPreferences("LoginToken", MODE_PRIVATE);
        token = preferences.getString("token", "");

        presenter = new TransactionPresenter(this, token);

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new PendingFragment(token), "Pending");
        adapter.addFragment(new ApprovedFragment(token), "Diproses");
        adapter.addFragment(new RentedFragment(token), "Disewa");
        adapter.addFragment(new DoneFragment(token), "Selesai");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        contractPresenter.loadTransaction();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(TransactionContract.Presenter presenter) {
        this.contractPresenter = presenter;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void closeProgress() {

    }

    @Override
    public void display(OrderHistoryData data) {

    }
}
