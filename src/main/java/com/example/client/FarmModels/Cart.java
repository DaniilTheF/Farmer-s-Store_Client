package com.example.client.FarmModels;

import java.io.Serializable;

public class Cart  implements Serializable {
    private int id;
    private int ordersId;
    private int productId;
    private String productName;
    private int personId;
    private int amount;
    private float price;
    private float total_price;

    public Cart() {
    }

    public Cart(int ordersId, int productId, String productName, int personId, int amount, float price, float total_price) {
        this.ordersId = ordersId;
        this.productId = productId;
        this.productName = productName;
        this.personId = personId;
        this.amount = amount;
        this.price = price;
        this.total_price += total_price;
    }

    public Cart(int id, int ordersId, int productId, String productName, int personId, int amount, float price, float total_price) {
        this.id = id;
        this.ordersId = ordersId;
        this.productId = productId;
        this.productName = productName;
        this.personId = personId;
        this.amount = amount;
        this.price = price;
        this.total_price += total_price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price += total_price;
    }


    @Override
    public String toString() {
        return  " Product name = '" + productName + '\'' +
                ", Amount = " + amount +
                ", Total cost for this product = " + price +
                ';';
    }
}
