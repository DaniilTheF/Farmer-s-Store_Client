package com.example.client.FarmModels;


public class History {
    private int id;
    private int ordersId;

    public History() {
    }

    public History(int id, int ordersId) {
        this.id = id;
        this.ordersId = ordersId;
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


    @Override
    public String toString() {
        return "History{" +
                "Added Order id: " + ordersId +
                '}';
    }
}
