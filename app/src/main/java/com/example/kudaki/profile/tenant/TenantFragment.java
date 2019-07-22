package com.example.kudaki.profile.tenant;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.kudaki.R;
import com.example.kudaki.transaction.TenantTransactionActivity;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TenantFragment extends Fragment {
    @BindView(R.id.tenantPending)
    ConstraintLayout pending;
    @BindView(R.id.tenantApproved)
    ConstraintLayout approved;
    @BindView(R.id.tenantRented)
    ConstraintLayout rented;
    @BindView(R.id.tenantDone)
    ConstraintLayout done;

    String token;

    public TenantFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tenant, container, false);
        ButterKnife.bind(this, view);

        Hawk.init(view.getContext()).build();

        token = Hawk.get("token");

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TenantTransactionActivity.class);
                intent.putExtra("status", "PENDING");
                startActivity(intent);
            }
        });

        approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TenantTransactionActivity.class);
                intent.putExtra("status", "APPROVED");
                startActivity(intent);
            }
        });

        rented.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TenantTransactionActivity.class);
                intent.putExtra("status", "RENTED");
                startActivity(intent);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TenantTransactionActivity.class);
                intent.putExtra("status", "DONE");
                startActivity(intent);
            }
        });
    }
}
