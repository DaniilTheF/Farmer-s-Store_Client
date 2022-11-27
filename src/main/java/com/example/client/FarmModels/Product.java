package com.example.client.FarmModels;


public class Product {
    private int id;
    private String name;
    private int quantity;
    private float cost;

    public Product() {
    }

    public Product(String name, int quantity, float cost) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    }

    public Product(int id, String name, int quantity, float cost) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getCost() {
        return cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", cost=" + cost +
                '}';
    }
}
