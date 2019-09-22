package com.example.mehmetkocakus.challenge.Retrofit;

import com.example.mehmetkocakus.challenge.Activities.Orders.Model.Order;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IAPI {

    @GET(".")
    Call<Order[]> getOrderArray();
}
