package com.example.client.FarmModels;

import java.io.Serializable;

public class Manager implements Serializable {

    private int id;
    private int personId;
    protected String qualification;
    protected int countOfOrder;

    public Manager() {
    }

    public Manager(int personId, String qualification, int countOfOrder) {
        this.personId = personId;
        this.qualification = qualification;
        this.countOfOrder = countOfOrder;
    }

    public Manager(int id, int personId, String qualification, int countOfOrder) {
        this.id = id;
        this.personId = personId;
        this.qualification = qualification;
        this.countOfOrder = countOfOrder;
    }

    public String getQualification(){
        return qualification;
    }
    public int getCountOfOrder(){
        return countOfOrder;
    }
    public void setQualification(String qualification){
        this.qualification = qualification;
    }
    public void setCountOfOrder(int countOfOrder){
        this.countOfOrder = countOfOrder;
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

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", personId=" + personId +
                ", qualification='" + qualification + '\'' +
                ", countOfOrder=" + countOfOrder +
                '}';
    }
}

