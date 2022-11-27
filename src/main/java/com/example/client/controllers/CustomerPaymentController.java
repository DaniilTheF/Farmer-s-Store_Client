package com.example.client.controllers;

import com.example.client.FarmModels.Customer;
import com.example.client.FarmModels.Purchase;
import com.example.client.Modules.Errors;
import com.example.client.connections.Connect;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerPaymentController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button topUpButton;

    @FXML
    private Button returnButton;

    @FXML
    private Button viewBalanceButton;

    @FXML
    private Button viewPurchaseButton;

    @FXML
    private Button orderPaymentButton;

    @FXML
    void topUpAccount(ActionEvent event) {
        topUpButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/client/CustomerPaymentToUp.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene((root)));
        stage.show();
    }
    @FXML
    void viewPurchase(ActionEvent event){
        Gson gson = new Gson();
        Purchase purchase = new Purchase();
        Connect.connection.sendMessage("ViewPurchase");
        purchase.setCustomerId(Connect.id);
        Connect.connection.sendObject(gson.toJson(purchase));
        String mes = "";
        try {
            mes = Connect.connection.readMessage();
        } catch(IOException ex){
            System.out.println("Error in reading");
        }
        if (mes.equals("No data"))
            Errors.showAlertWithNoData();
        else {
            String obj = Connect.connection.readObject().toString();
            Type fooType = new TypeToken<ArrayList<Purchase>>() {}.getType();
            ArrayList<Purchase> r =  gson.fromJson(obj,fooType);
            ListView<Purchase> a = new ListView<>();
            for(Purchase p: r){
                a.getItems().add(p);
            }

            VBox vBox = new VBox(a);

            Scene scene = new Scene(vBox, 1000, 400);

            Stage stage = new Stage();
            stage.setTitle("Out Put Information");
            stage.setScene(scene);
            stage.showAndWait();
        }
    }
    @FXML
    void viewBalance(ActionEvent event) {
        Gson gson = new Gson();
        Customer customer = new Customer();
        Connect.connection.sendMessage("ViewBalance");
        customer.setPersonId(Connect.id);
        Connect.connection.sendObject(gson.toJson(customer));
        String mes = "";
        try {
            mes = Connect.connection.readMessage();
        } catch(IOException ex){
            System.out.println("Error in reading");
        }
        if (mes.equals("No data"))
            Errors.showAlertWithNoData();
        else {
            String obj = Connect.connection.readObject().toString();
            Type fooType = new TypeToken<ArrayList<Customer>>() {}.getType();
            ArrayList<Customer> r =  gson.fromJson(obj,fooType);
            ListView<Customer> a = new ListView<>();
            for(Customer p: r){
                a.getItems().add(p);
            }

            VBox vBox = new VBox(a);

            Scene scene = new Scene(vBox, 1000, 400);

            Stage stage = new Stage();
            stage.setTitle("Out Put Information");
            stage.setScene(scene);
            stage.showAndWait();
        }
    }
    @FXML
    void orderPayment(ActionEvent event) {
        orderPaymentButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/client/CustomerPaymentOrderPayment.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene((root)));
        stage.show();
    }

    @FXML
    void initialize() {
        returnButton.setOnAction(event -> {
            returnButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/client/CustomerMenu.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene((root)));
            stage.show();
        });
    }

}
