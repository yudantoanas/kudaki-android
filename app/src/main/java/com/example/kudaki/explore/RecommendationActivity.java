package com.example.kudaki.explore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kudaki.R;
import com.example.kudaki.adapter.RecommendationAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecommendationActivity extends AppCompatActivity {
    @BindView(R.id.rvRecommendation)
    RecyclerView recyclerView;

    String token;
    RecommendationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        ButterKnife.bind(this);
    }
}
