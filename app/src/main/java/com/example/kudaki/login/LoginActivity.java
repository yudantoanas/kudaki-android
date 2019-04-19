package com.example.kudaki.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kudaki.MainActivity;
import com.example.kudaki.R;
import com.example.kudaki.forgotpwd.ForgotPwdActivity;
import com.example.kudaki.register.RegisterActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.loginEmail) EditText email;
    @BindView(R.id.loginPassword) EditText password;
    @BindView(R.id.submitLogin) Button button;
    @BindView(R.id.linkSignup) TextView linkSignup;
    @BindView(R.id.linkForgotPwd) TextView linkForgotPwd;
    @BindView(R.id.buttonLoginGoogle) ImageView buttonLoginGoogle;

    ProgressDialog progressDialog;
    LoginPresenter loginPresenter;
    LoginContract.Presenter contractPresenter;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);

        // assign presenter to this activity
        loginPresenter = new LoginPresenter(this);

        /* Google Sign In */
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
        /* End of Google Sign In */
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        button.setOnClickListener(v ->
                contractPresenter.doLogin(email.getText().toString(), password.getText().toString()));

        buttonLoginGoogle.setOnClickListener(view -> {
            Intent googleLogin = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(googleLogin, 1);
        });

        linkSignup.setOnClickListener(v -> {
            contractPresenter.linkSignupClicked();
        });

        linkForgotPwd.setOnClickListener(v -> {
            contractPresenter.linkForgotPwdClicked();
        });
    }

    // on activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // if result code from Register Activity is OK
        if (resultCode == RESULT_OK) {
            this.finish(); // Finish this Login Activity
        }

        if (requestCode == 1) { // request code 1 = Google Sign In
            contractPresenter.doGoogleLogin(data);
        }
    }

    @Override
    public void setPresenter(LoginContract.Presenter contractPresenter) {
        this.contractPresenter = contractPresenter;
    }

    @Override
    public void showOnLoginSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent home = new Intent(this, MainActivity.class);

//        // set shared preference isLogin = true
//        SharedPreferences sharedPreferences = this.getSharedPreferences("loginStatus",MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putBoolean("isLogin", true)
//                .apply(); // save preference asynchronously

        // start home activity then destroy this activity
        startActivity(home);
        finish();
    }

    @Override
    public void showOnLoginFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSignupActivity(int requestCode) {
        Intent signup = new Intent(this, RegisterActivity.class);
        startActivityForResult(signup, requestCode);
    }

    @Override
    public void showForgotPwdActivity() {
        Intent forgot = new Intent(this, ForgotPwdActivity.class);
        startActivity(forgot);
    }

    @Override
    public void updateUI(GoogleSignInAccount account) {
        /**
         * if GoogleSignIn.getLastSignedInAccount returns a GoogleSignInAccount object (not null),
         * that means the user has already signed in to your app with Google.
         * Update your UI accordingly—that is, hide the sign-in button, launch your main activity,
         * or whatever is appropriate for your app.
         *
         * If GoogleSignIn.getLastSignedInAccount returns null,
         * that means the user has not yet signed in to your app with Google.
         * Update your UI to display the Google Sign-in button.
         */
        if (account != null) {
            showOnLoginSuccess("Berhasil Login dengan Google");
        }
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