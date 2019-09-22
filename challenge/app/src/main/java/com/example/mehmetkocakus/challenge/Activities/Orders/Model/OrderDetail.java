package com.example.mehmetkocakus.challenge.Activities.Orders.Model;

import com.google.gson.annotations.SerializedName;

public class OrderDetail {

    @SerializedName("orderDetail")
    private String orderDetail;

    @SerializedName("summaryPrice")
    private Double summaryPrice;

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Double getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(Double summaryPrice) {
        this.summaryPrice = summaryPrice;
    }
}
