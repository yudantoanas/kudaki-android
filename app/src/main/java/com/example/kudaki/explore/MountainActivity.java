package com.example.kudaki.explore;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import com.bumptech.glide.Glide;
import com.example.kudaki.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MountainActivity extends AppCompatActivity {
    @BindView(R.id.mountainToolbar)
    Toolbar toolbar;
    @BindView(R.id.mountainName)
    TextView name;
    @BindView(R.id.mountainDesc)
    TextView description;
    @BindView(R.id.mountainHeight)
    TextView height;
    @BindView(R.id.mountainDifficulty)
    TextView difficulty;
    @BindView(R.id.mountainImage)
    ImageView image;
    @BindView(R.id.fabGoogleMap)
    FloatingActionButton floatingActionButton;


    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bundle = getIntent().getExtras();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Glide.with(this)
                .load(bundle.getString("photo"))
                .into(image);
        name.setText(bundle.getString("name"));
        description.setText(bundle.getString("description"));
        difficulty.setText(String.valueOf(bundle.getDouble("difficulty", 0)));
        height.setText(bundle.getInt("height") + " Mdpl");
    }

    @Override
    protected void onResume() {
        super.onResume();

        floatingActionButton.setOnClickListener(v -> {
            Toast.makeText(v.getContext(),
                    "Latitude: " + bundle.getDouble("latitude") + "," +
                            "Longitude: " + bundle.getDouble("longitude"), Toast.LENGTH_SHORT).show();
        });
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
}
