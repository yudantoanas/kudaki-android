package com.example.kudaki;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.kudaki.event.EventFragment;
import com.example.kudaki.explore.ExploreFragment;
import com.example.kudaki.home.HomeFragment;
import com.example.kudaki.profile.ProfileFragment;
import com.example.kudaki.renting.RentingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    @BindView(R.id.bottomNav)
    BottomNavigationView bottomNav;

    MainPresenter mainPresenter;
    MainContract.Presenter contractPresenter;
//    GoogleSignInClient mGoogleSignInClient;

    int backPressCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this);

        // load default fragment
        loadFragment(new HomeFragment());

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .requestProfile()
//                .build();

        // Build a GoogleSignInClient with the options specified by gso.
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void onResume() {
        super.onResume();

        bottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.home:
//                    toolbar.setTitle(R.string.home_ID);
                    loadFragment(new HomeFragment());
                    return true;
                case R.id.event:
//                    toolbar.setTitle(R.string.event_ID);
                    loadFragment(new EventFragment());
                    return true;
                case R.id.explore:
//                    toolbar.setTitle(R.string.explore_ID);
                    loadFragment(new ExploreFragment());
                    return true;
                case R.id.renting:
//                    toolbar.setTitle(R.string.renting_ID);
                    loadFragment(new RentingFragment());
                    return true;
                case R.id.profile:
//                    toolbar.setTitle(R.string.profile_ID);
                    loadFragment(new ProfileFragment());
                    return true;
            }
            return false;
        });
    }

    @Override
    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame, fragment);
        transaction.commit();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.contractPresenter = presenter;
    }

    @Override
    public void onBackPressed() {
        backPressCount++;
        if (backPressCount < 2) {
            Toast.makeText(this, "Press back button two times", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Exiting app", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        // destroy loginStatus preference
//        SharedPreferences loginStatus = getSharedPreferences("loginStatus", MODE_PRIVATE);
//        SharedPreferences.Editor editor = loginStatus.edit();
//        editor.remove("isLogin");
//        editor.apply();
    }
}
