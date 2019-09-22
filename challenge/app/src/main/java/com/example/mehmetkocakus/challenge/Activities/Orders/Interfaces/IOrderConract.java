package com.example.mehmetkocakus.challenge.Activities.Orders.Interfaces;

import com.example.mehmetkocakus.challenge.Activities.Orders.Model.Order;

import java.util.List;

public interface IOrderConract {

    //bu metot sipariş listesini çeker
    void getOrderList();

    interface UserInterface{
        //bu metot çekilen sipariş listesini activity'e düşürür
        void resultListOrder(List<Order> listOfResult);
    }
}
