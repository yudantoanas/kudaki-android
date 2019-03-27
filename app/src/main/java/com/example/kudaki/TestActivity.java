package com.example.kudaki;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TestActivity extends AppCompatActivity implements MainContract.View {
    @BindView(R.id.bottomNav) BottomNavigationView bottomNav;
    Button btnLogout;
    ActionBar toolbar;

    MainPresenter mainPresenter;
    MainContract.Presenter contractPresenter;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        toolbar = getSupportActionBar();

        // load default fragment
        toolbar.setTitle("Home");

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void onResume() {
        super.onResume();

        bottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.home:
                    toolbar.setTitle("Home");
                    return true;
                case R.id.event:
                    toolbar.setTitle("Events");
                    return true;
                case R.id.explore:
                    toolbar.setTitle("Explore");
                    return true;
                case R.id.renting:
                    toolbar.setTitle("Renting");
                    return true;
                case R.id.profile:
                    toolbar.setTitle("Profile");
                    return true;
            }
            return false;
        });

        /*btnLogout.setOnClickListener(view -> {
            mGoogleSignInClient.signOut(); // Signs out google account if any

            // logout code...
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
            finish();
        });*/
    }

    @Override
    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.contractPresenter = presenter;
    }
}
