package com.example.kudaki.profile;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.kudaki.MainActivity;
import com.example.kudaki.R;
import com.example.kudaki.event.EventActivity;
import com.example.kudaki.explore.ExploreActivity;
import com.example.kudaki.model.response.AddressData;
import com.example.kudaki.model.response.ProfileData;
import com.example.kudaki.profile.etalase.EtalaseActivity;
import com.example.kudaki.renting.RentalActivity;
import com.example.kudaki.setting.SettingActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.View {
    @BindView(R.id.profileToolbar)
    Toolbar toolbar;
    @BindView(R.id.profileNav)
    BottomNavigationView bottomNav;
    @BindView(R.id.profileEdit)
    ConstraintLayout profileEdit;
    @BindView(R.id.profileName)
    TextView name;
    @BindView(R.id.profilePhone)
    TextView phone;
    @BindView(R.id.profileBegin)
    TextView profileBegin;
    @BindView(R.id.profileEtalase)
    ConstraintLayout profileEtalase;

    String fullName, phoneNumber, token;

    ProfileContract.Presenter contractPresenter;
    ProfilePresenter profilePresenter;

    ProgressDialog progressDialog;

    SharedPreferences user;
    SharedPreferences loginToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        user = getSharedPreferences("User", MODE_PRIVATE);
        loginToken = getSharedPreferences("LoginToken", MODE_PRIVATE);

        // get token
        token = loginToken.getString("token", "");

        profilePresenter = new ProfilePresenter(this, token);

        progressDialog = new ProgressDialog(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // load profile
        if (user.getAll().isEmpty()) {
            contractPresenter.loadProfile();
        } else {
            name.setText(user.getString("name", ""));
            phone.setText(user.getString("phone", ""));
        }

        // load address
        contractPresenter.loadAddress();
    }

    @Override
    protected void onResume() {
        super.onResume();

        bottomNav.getMenu().getItem(4).setChecked(true);
        bottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navHome:
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                    return true;
                case R.id.navEvent:
                    startActivity(new Intent(this, EventActivity.class));
                    finish();
                    return true;
                case R.id.navExplore:
                    startActivity(new Intent(this, ExploreActivity.class));
                    finish();
                    return true;
                case R.id.navRental:
                    startActivity(new Intent(this, RentalActivity.class));
                    finish();
                    return true;
                case R.id.navProfile:
                    return true;
            }
            return false;
        });

        profileEdit.setOnClickListener(v -> {
            Intent edit = new Intent(v.getContext(), EditProfileActivity.class);
            edit.putExtra("name", user.getString("name", ""));
            edit.putExtra("phone", user.getString("phone", ""));
            startActivity(edit);
        });

        profileBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                // Get the layout inflater
                LayoutInflater inflater = getLayoutInflater();

                View view = inflater.inflate(R.layout.dialog_address, null);
                EditText addr = view.findViewById(R.id.profileInputAddress);

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(view)
                        // Add action buttons
                        .setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                contractPresenter.saveAddress(addr.getText().toString());
                            }
                        })
                        .setNegativeButton("Tutup", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                builder.show();
            }
        });

        profileEtalase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent etalase = new Intent(v.getContext(), EtalaseActivity.class);
                etalase.putExtra("token", token);
                startActivity(etalase);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                Intent setting = new Intent(this, SettingActivity.class);
                setting.putExtra("token", token);
                startActivity(setting);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        this.contractPresenter = presenter;
    }

    @Override
    public void display(ProfileData data) {
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("uuid", data.getProfile().getUuid());
        editor.putString("name", data.getProfile().getFullName());
        editor.putString("phone", data.getProfile().getPhoneNumber());
        editor.apply();

        fullName = data.getProfile().getFullName();
        phoneNumber = data.getProfile().getPhoneNumber();

        name.setText(fullName);
        phone.setText(phoneNumber);
    }

    @Override
    public void checkAddress(AddressData data) {
        if (data.getAddresses() == null) {
            profileBegin.setVisibility(View.VISIBLE);
            profileEtalase.setVisibility(View.INVISIBLE);
        } else {
            profileBegin.setVisibility(View.INVISIBLE);
            profileEtalase.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showBeginSuccess() {
        profileBegin.setVisibility(View.INVISIBLE);
        profileEtalase.setVisibility(View.VISIBLE);
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
