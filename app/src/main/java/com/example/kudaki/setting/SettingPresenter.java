package com.example.kudaki.setting;

import android.content.Context;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class SettingPresenter implements SettingContract.Presenter {

    private SettingContract.View settingView;
    GoogleSignInClient googleSignInClient;

    public SettingPresenter(SettingContract.View settingView) {
        this.settingView = settingView;
        this.settingView.setPresenter(this);
    }

    @Override
    public void doLogout(Context context) {
        if (AccessToken.getCurrentAccessToken() != null) {
            LoginManager.getInstance().logOut();
        }

        if (GoogleSignIn.getLastSignedInAccount(context) != null) {
            googleSignInClient.signOut();
        }

        settingView.showLogoutSuccess("Berhasil Keluar");
    }

    @Override
    public void onBackClicked() {

    }
}
