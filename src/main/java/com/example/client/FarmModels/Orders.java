package com.example.client.FarmModels;


import java.io.Serializable;

public class Orders  implements Serializable {
    private int id;
    private float total_price;

    private int personId;
    public Orders() {
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public Orders(int id, float total_price, int personId) {
        this.id = id;
        this.total_price = total_price;
        this.personId= personId;
    }

    public int getId() {
        return id;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", total_price=" + total_price +
                '}';
    }
}
