package com.example.mehmetkocakus.challenge.Activities.Orders.Presenter;

import android.app.Activity;
import android.app.ProgressDialog;

import com.example.mehmetkocakus.challenge.Activities.Orders.Interfaces.IOrderConract;
import com.example.mehmetkocakus.challenge.Activities.Orders.Model.Order;
import com.example.mehmetkocakus.challenge.R;
import com.example.mehmetkocakus.challenge.Retrofit.APICient;
import com.example.mehmetkocakus.challenge.Retrofit.IAPI;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderPresenter implements IOrderConract {

    IOrderConract.UserInterface userInterface;

    Activity activity;

    public OrderPresenter(Activity activity)
    {
        userInterface=(IOrderConract.UserInterface) activity;
        this.activity=activity;
    }


    //Bu metot retrofit aracılığıyla web servisden verileri çeker
    @Override
    public void getOrderList() {
        APICient apiClient=new APICient(activity.getResources().getString(R.string.URL_string));
        IAPI apiInterface =
                apiClient.getRetrofitInstance().create(IAPI.class);

        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(activity.getResources().getString(R.string.orders_listing_string));
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();


        Call<Order[]> call = apiInterface.getOrderArray();
        call.enqueue(new Callback<Order[]>() {
            @Override
            public void onResponse(Call<Order[]> call, Response<Order[]> response) {
                userInterface.resultListOrder(Arrays.asList(response.body()));
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Order[]> call, Throwable t) {
                userInterface.resultListOrder(new ArrayList<>());
                progressDialog.dismiss();
            }
        });
    }
}
