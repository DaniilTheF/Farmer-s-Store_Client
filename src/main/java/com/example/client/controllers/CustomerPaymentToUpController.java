package com.example.client.controllers;

import com.example.client.FarmModels.Customer;
import com.example.client.Modules.Errors;
import com.example.client.connections.Connect;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerPaymentToUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField quantity;

    @FXML
    private Button topUpButton;

    @FXML
    private Button returnButton;
    private Integer tryParse(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    @FXML
    void topUpAccount(ActionEvent event) {
        Gson gson = new Gson();
        if (checkInput()) {
            Errors.showAlertWithNullInput();
        } else {
            System.out.println("Start enter");
            Customer customer = new Customer();
            customer.setPersonId(Connect.id);
            if(!(tryParse(quantity.getText())>0)){
               Errors.showAlertWithData();
            }
            customer.setMoney(tryParse(quantity.getText()));
            Connect.connection.sendMessage("AddMoney");
            Connect.connection.sendObject(gson.toJson(customer));
            System.out.println("Запись отправлена");

                topUpButton.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/client/CustomerPayment.fxml"));

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
        }

    @FXML
    void initialize() {
        returnButton.setOnAction(event -> {
            returnButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/client/CustomerPayment.fxml"));

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
    private boolean checkInput() {
        try {
            return quantity.getText().equals("");
        }
        catch (Exception e) {
            System.out.println("Error");
            return true;
        }
    }

}
