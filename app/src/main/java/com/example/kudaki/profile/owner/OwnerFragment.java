package com.example.kudaki.profile.owner;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.kudaki.R;
import com.example.kudaki.profile.etalase.EtalaseActivity;
import com.example.kudaki.transaction.OwnerTransactionActivity;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OwnerFragment extends Fragment implements OwnerContract.View{
    @BindView(R.id.ownerPending)
    ConstraintLayout pending;
    @BindView(R.id.ownerAccepted)
    ConstraintLayout approved;
    @BindView(R.id.ownerRented)
    ConstraintLayout rented;
    @BindView(R.id.ownerDone)
    ConstraintLayout done;
    @BindView(R.id.ownerEtalase)
    CardView etalase;
    @BindView(R.id.ownerAddItem)
    Button btnAdd;

    String token;

    OwnerContract.Presenter contractPresenter;
    OwnerPresenter presenter;

    ProgressDialog progressDialog;

    public OwnerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_owner, container, false);
        ButterKnife.bind(this, view);

        Hawk.init(view.getContext()).build();

        token = Hawk.get("token");

        presenter = new OwnerPresenter(this, token);

        progressDialog = new ProgressDialog(view.getContext());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), OwnerTransactionActivity.class);
                intent.putExtra("status", "PENDING");
                startActivity(intent);
            }
        });

        approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), OwnerTransactionActivity.class);
                intent.putExtra("status", "APPROVED");
                startActivity(intent);
            }
        });

        rented.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), OwnerTransactionActivity.class);
                intent.putExtra("status", "RENTED");
                startActivity(intent);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), OwnerTransactionActivity.class);
                intent.putExtra("status", "DONE");
                startActivity(intent);
            }
        });

        etalase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EtalaseActivity.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.CustomDialogTheme);

                LayoutInflater inflater = getLayoutInflater();

                View view = inflater.inflate(R.layout.dialog_etalase_add, null);
                EditText name = view.findViewById(R.id.etalaseAddName);
                EditText desc = view.findViewById(R.id.etalaseAddDesc);
                EditText price = view.findViewById(R.id.etalaseAddPrice);

                builder.setView(view)
                        // Add action buttons
                        .setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
//                                String durr = "";
//                                if (duration.getText().toString().equalsIgnoreCase("harian")){
//                                    durr = "DAY";
//                                } else if (duration.getText().toString().equalsIgnoreCase("mingguan")) {
//                                    durr = "WEEK";
//                                } else if (duration.getText().toString().equalsIgnoreCase("bulanan")) {
//                                    durr = "MONTH";
//                                } else {
//                                    durr = "YEAR";
//                                }

                                contractPresenter.addItem(
                                        name.getText().toString(),
                                        desc.getText().toString(),
                                        price.getText().toString(),
                                        "DAY");

                                dialog.dismiss();
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
    public void showAddSuccess(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), EtalaseActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(OwnerContract.Presenter presenter) {
        this.contractPresenter = presenter;
    }
}
