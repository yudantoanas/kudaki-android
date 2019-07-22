package com.example.kudaki.transaction;

public class OwnerTransactionPresenter implements OwnerTransactionContract.Presenter {
    String token;
    OwnerTransactionContract.View view;

    public OwnerTransactionPresenter(OwnerTransactionContract.View view, String token) {
        this.token = token;
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadTransaction(String status) {

    }

    @Override
    public void start() {

    }
}
