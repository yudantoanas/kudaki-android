package com.example.kudaki.setting;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingPresenter implements SettingContract.Presenter {

    private SettingContract.View settingView;

    public SettingPresenter(SettingContract.View settingView) {
        this.settingView = settingView;
        this.settingView.setPresenter(this);
    }

    @Override
    public void doLogout(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginToken", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

//        if (AccessToken.getCurrentAccessToken() != null) {
//            LoginManager.getInstance().logOut();
//        }
//
//        if (GoogleSignIn.getLastSignedInAccount(context) != null) {
//            googleSignInClient.signOut();
//        }

        settingView.showLogoutSuccess("Berhasil Keluar");
    }

    @Override
    public void start() {

    }
}
