package com.example.kudaki.explore;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kudaki.R;
import com.example.kudaki.adapter.RecommendationAdapter;
import com.example.kudaki.model.response.RecommendationData;
import com.example.kudaki.model.response.RecommendedGear;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecommendationActivity extends AppCompatActivity implements RecommendationContract.View {
    @BindView(R.id.recommendationToolbar)
    Toolbar toolbar;
    @BindView(R.id.rvRecommendation)
    RecyclerView recyclerView;

    String token, uuid;
    RecommendationAdapter adapter;

    RecommendationContract.Presenter contractPresenter;
    RecommendationPresenter presenter;

    ArrayList<RecommendedGear> list;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Hawk.init(this).build();

        token = Hawk.get("token");
        uuid = getIntent().getExtras().getString("uuid");

        presenter = new RecommendationPresenter(this, token, uuid);

        progressDialog = new ProgressDialog(this);

        list = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        contractPresenter.loadRecommendation();
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
    public void showProgress() {
        progressDialog.setMax(100);
        progressDialog.setMessage("Please wait...");
        progressDialog.setTitle("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    public void closeProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showData(RecommendationData data) {
        if (data.getRecommendedGears() == null) {
            Toast.makeText(this, "Belum ada rekomendasi alat", Toast.LENGTH_SHORT).show();
        } else {
            list.clear();
            for (int i = 0; i < data.getRecommendedGears().size(); i++) {
                list.add(new RecommendedGear(
                        data.getRecommendedGears().get(i).getUuid(),
                        data.getRecommendedGears().get(i).getSeen(),
                        data.getRecommendedGears().get(i).getCreatorFullName()
                ));
            }
            adapter = new RecommendationAdapter(this, list);
            adapter.notifyDataSetChanged();
            adapter.setToken(token);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void setPresenter(RecommendationContract.Presenter presenter) {
        this.contractPresenter = presenter;
    }
}
