package com.example.client.FarmModels;
public class Earned {
    private int id;
    private int earned;

    public Earned(){
    }

    public Earned(int earned) {
        this.earned = earned;
    }

    public Earned(int id, int earned) {
        this.id = id;
        this.earned = earned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEarned() {
        return earned;
    }

    public void setEarned(int earned) {
        this.earned = earned;
    }

    @Override
    public String toString() {
        return "Earned{" +
                "All earned money=" + earned +
                '}';
    }
}
