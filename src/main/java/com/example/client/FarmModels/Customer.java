package com.example.client.FarmModels;

import java.io.Serializable;

public class Customer implements Serializable {

    private int id;
    private int personId;
    protected String adress;
    protected float money;

    public Customer() {
    }
    public Customer(int personId, String adress, float money) {
        this.personId = personId;
        this.adress = adress;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public Customer(int id, int personId, String adress, float money) {
        this.id = id;
        this.personId = personId;
        this.adress = adress;
        this.money = money;
    }

    public String getAdress(){
        return adress;
    }
    public float getMoney(){
        return money;
    }
    public void setAdress(String adress){
        this.adress = adress;
    }
    public void setMoney(int money){
        this.money = money;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "money=" + money +
                '}';
    }
}