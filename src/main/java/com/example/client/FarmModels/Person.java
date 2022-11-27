package com.example.client.FarmModels;


import java.io.Serializable;
public class Person implements Serializable {

    protected int id;
    protected String name;
    protected String surname;
    protected String phone;
    protected String login;
    protected String password;
    protected String role;
    protected boolean banned;

    public Person(){
    }

    public Person(String name, String surname, String phone, String login, String password, String role, boolean banned) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.login = login;
        this.password = password;
        this.role = role;
        this.banned = banned;
    }
    public Person(int id, String name, String surname, String phone, String login, String password, String role,boolean banned) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.login = login;
        this.password = password;
        this.role = role;
        this.banned = banned;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int  getId(){return id;}

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}


