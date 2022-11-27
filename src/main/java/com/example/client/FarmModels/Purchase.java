package com.example.client.FarmModels;


import java.io.Serializable;

public class Purchase implements Serializable {
    private int id;
    private int orderId;
    private int customerId;
    private boolean sent;
    public Purchase(){
    }

    public Purchase(int orderId, int customerId, boolean sent) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.sent = sent;
    }

    public Purchase(int id, int orderId, int customerId, boolean sent) {
        this.id = id;
        this.orderId = orderId;
        this.customerId = customerId;
        this.sent = sent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "Id= " + id +
                " ,orderId=" + orderId +
                ", sent=" + sent +
                '}';
    }

}
