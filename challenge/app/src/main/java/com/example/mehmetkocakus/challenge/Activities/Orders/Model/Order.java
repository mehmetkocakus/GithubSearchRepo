package com.example.mehmetkocakus.challenge.Activities.Orders.Model;

import com.google.gson.annotations.SerializedName;

public  class Order{

    @SerializedName("date")
    private String data;

    @SerializedName("month")
    private String month;

    @SerializedName("marketName")
    private String marketName;

    @SerializedName("orderName")
    private String orderName;

    @SerializedName("productPrice")
    private Double productPrice;

    @SerializedName("productState")
    private String productState;

    @SerializedName("productDetail")
    private OrderDetail productDetail;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }

    public OrderDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(OrderDetail productDetail) {
        this.productDetail = productDetail;
    }
}
