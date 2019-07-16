package com.example.kudaki.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kudaki.R;
import com.example.kudaki.adapter.CartAdapter;
import com.example.kudaki.model.response.CartData;
import com.example.kudaki.model.response.Storefront;
import com.example.kudaki.transaction.TransactionActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity implements CartContract.View{
    @BindView(R.id.cartToolbar)
    Toolbar toolbar;
    @BindView(R.id.rvCart)
    RecyclerView recyclerView;
    @BindView(R.id.cartCheckout)
    Button btnCheckout;
    @BindView(R.id.cartTotalPrice)
    TextView totalPrice;

    String cartUuid;

    ArrayList<Storefront> list;
    CartAdapter adapter;

    CartContract.Presenter contractPresenter;
    CartPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new CartPresenter(this, getIntent().getExtras().getString("token"));

        list = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        contractPresenter.loadItems();
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contractPresenter.checkout(cartUuid);
            }
        });
    }

    @Override
    public void showCartItems(CartData data) {
        cartUuid = String.valueOf(data.getCart().getUuid());
        totalPrice.setText(String.valueOf(data.getCart().getTotalPrice()));

        if (data.getStorefronts() == null) {
            Toast.makeText(this, "Keranjang masih kosong", Toast.LENGTH_SHORT).show();
        } else {
            list.clear();
            for (int i = 0; i < data.getStorefronts().size(); i++) {
                list.add(new Storefront(
                        data.getStorefronts().get(i).getOwnerName(),
                        data.getStorefronts().get(i).getOwnerEmail(),
                        data.getStorefronts().get(i).getOwnerPhoneNumber(),
                        data.getStorefronts().get(i).getCartItems()
                ));
            }
            adapter = new CartAdapter(this, list);
            adapter.notifyDataSetChanged();
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void showCheckoutSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent transaction = new Intent(this, TransactionActivity.class);
        startActivity(transaction);
    }

    @Override
    public void setPresenter(CartContract.Presenter presenter) {
        this.contractPresenter = presenter;
    }
}
