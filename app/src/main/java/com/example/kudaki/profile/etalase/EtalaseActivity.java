package com.example.kudaki.profile.etalase;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kudaki.R;
import com.example.kudaki.adapter.EtalaseAdapter;
import com.example.kudaki.model.response.StoreData;
import com.example.kudaki.model.response.StoreItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EtalaseActivity extends AppCompatActivity implements EtalaseContract.View {
    @BindView(R.id.etalaseToolbar)
    Toolbar toolbar;
    @BindView(R.id.rvEtalase)
    RecyclerView rvEtalase;
    @BindView(R.id.etalaseFabAdd)
    FloatingActionButton fabAdd;

    String token;

    EtalaseAdapter adapter;
    ArrayList<StoreItem> list;

    EtalaseContract.Presenter contractPresenter;
    EtalasePresenter presenter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etalase);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Hawk.init(this).build();

        token = Hawk.get("token");

        presenter = new EtalasePresenter(this, token);

        list = new ArrayList<>();

        progressDialog = new ProgressDialog(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        contractPresenter.loadItems();
    }

    @Override
    protected void onResume() {
        super.onResume();

        fabAdd.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.CustomDialogTheme);

            LayoutInflater inflater = getLayoutInflater();

            View view = inflater.inflate(R.layout.dialog_etalase_add, null);
            EditText name = view.findViewById(R.id.etalaseAddName);
            EditText desc = view.findViewById(R.id.etalaseAddDesc);
            EditText price = view.findViewById(R.id.etalaseAddPrice);

            builder.setView(view)
                    .setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
//                            String durr = "";
//                            if (duration.getText().toString().equalsIgnoreCase("harian")){
//                                durr = "DAY";
//                            } else if (duration.getText().toString().equalsIgnoreCase("mingguan")) {
//                                durr = "WEEK";
//                            } else if (duration.getText().toString().equalsIgnoreCase("bulanan")) {
//                                durr = "MONTH";
//                            } else {
//                                durr = "YEAR";
//                            }

                            contractPresenter.addItem(
                                    name.getText().toString(),
                                    desc.getText().toString(),
                                    price.getText().toString(),
                                    "DAY");
                        }
                    })
                    .setNegativeButton("Tutup", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        });
    }

    @Override
    public void setPresenter(EtalaseContract.Presenter presenter) {
        this.contractPresenter = presenter;
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
    public void showEtalaseItem(StoreData data) {
        if (data.getItems() == null) {
            Toast.makeText(this, "Etalase kosong", Toast.LENGTH_SHORT).show();
        } else {
            list.clear();
            for (int i = 0; i < data.getItems().size(); i++) {
                list.add(new StoreItem(
                        data.getItems().get(i).getUuid(),
                        data.getItems().get(i).getStorefrontUuid(),
                        data.getItems().get(i).getName(),
                        data.getItems().get(i).getPrice(),
                        data.getItems().get(i).getDescription(),
                        data.getItems().get(i).getPhoto(),
                        data.getItems().get(i).getRating(),
                        data.getItems().get(i).getPriceDuration()
                ));
            }
            adapter = new EtalaseAdapter(this, list);
            adapter.setToken(token);
            adapter.notifyDataSetChanged();
            rvEtalase.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            rvEtalase.setAdapter(adapter);
        }
    }

    @Override
    public void showAddSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(this, EtalaseActivity.class));
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
}
