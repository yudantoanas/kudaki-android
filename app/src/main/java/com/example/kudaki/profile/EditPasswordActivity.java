package com.example.kudaki.profile;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import com.example.kudaki.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditPasswordActivity extends AppCompatActivity implements EditPasswordContract.View {
    @BindView(R.id.btnSavePassword)
    Button btnSave;
    @BindView(R.id.editPasswordToolbar)
    Toolbar toolbar;
    @BindView(R.id.oldPassword)
    EditText oldPwd;
    @BindView(R.id.newPassword)
    EditText newPwd;

    EditPasswordPresenter presenter;
    EditPasswordContract.Presenter contractPresenter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);

        presenter = new EditPasswordPresenter(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnSave.setOnClickListener(v -> contractPresenter.changePassword(oldPwd, newPwd));
    }

    @Override
    public void setPresenter(EditPasswordContract.Presenter presenter) {
        this.contractPresenter = presenter;
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

    @Override
    public void showChangeSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        NavUtils.navigateUpFromSameTask(this);
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
    public void showChangeFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
