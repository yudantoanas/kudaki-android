package com.example.kudaki;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.kudaki.event.EventFragment;
import com.example.kudaki.explore.ExploreFragment;
import com.example.kudaki.home.HomeFragment;
import com.example.kudaki.login.LoginActivity;
import com.example.kudaki.profile.ProfileFragment;
import com.example.kudaki.renting.RentingFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    @BindView(R.id.bottomNav) BottomNavigationView bottomNav;
    ActionBar toolbar;

    MainPresenter mainPresenter;
    MainContract.Presenter contractPresenter;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this);

        toolbar = getSupportActionBar();

        // set default title
        toolbar.setTitle(R.string.home_ID);
        // load default fragment
        loadFragment(new HomeFragment());

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
            switch (menuItem.getItemId()) {
                case R.id.home:
                    toolbar.setTitle(R.string.home_ID);
                    loadFragment(new HomeFragment());
                    return true;
                case R.id.event:
                    toolbar.setTitle(R.string.event_ID);
                    loadFragment(new EventFragment());
                    return true;
                case R.id.explore:
                    toolbar.setTitle(R.string.explore_ID);
                    loadFragment(new ExploreFragment());
                    return true;
                case R.id.renting:
                    toolbar.setTitle(R.string.renting_ID);
                    loadFragment(new RentingFragment());
                    return true;
                case R.id.profile:
                    toolbar.setTitle(R.string.profile_ID);
                    loadFragment(new ProfileFragment());
                    return true;
            }
            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuLogout :
                mGoogleSignInClient.signOut(); // Signs out google account if any
                // logout code...

                Intent activity_login = new Intent(this, LoginActivity.class);
                startActivity(activity_login);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
        super.onBackPressed();
        Toast.makeText(this, "Back Button is Pressed", Toast.LENGTH_SHORT).show();
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
