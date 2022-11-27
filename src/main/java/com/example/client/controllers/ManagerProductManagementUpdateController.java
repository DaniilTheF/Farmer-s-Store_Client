package com.example.client.controllers;

import com.example.client.FarmModels.Product;
import com.example.client.Modules.Errors;
import com.example.client.connections.Connect;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerProductManagementUpdateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button updateButton;

    @FXML
    private PasswordField idField;

    @FXML
    private TextField name;

    @FXML
    private TextField cost;

    @FXML
    private Button returnButton;

    @FXML
    private TextField quantity;
    private Integer tryParse(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    @FXML
    void updateProduct(ActionEvent event) {
        Gson gson = new Gson();
        String mes = "";
        if (checkInput()) {
            Errors.showAlertWithNullInput();
        } else {
            System.out.println("Start enter");
            Product product = new Product();
            product.setId(Integer.parseInt(idField.getText()));
            product.setName(name.getText());
            product.setQuantity(tryParse(quantity.getText()));
            if (!(tryParse(quantity.getText()) > 0 && tryParse(cost.getText()) > 0)) {
                Errors.showAlertWithData();
            }
            product.setQuantity(tryParse(quantity.getText()));
            product.setCost(tryParse(cost.getText()));
            Connect.connection.sendMessage("UpdateProduct");
            Connect.connection.sendObject(gson.toJson(product));
            System.out.println("Запись отправлена");
            try {
                mes = Connect.connection.readMessage();
            } catch (IOException ex) {
                System.out.println("Error in reading");
            }
            if (!mes.equals("OK")){
                Errors.showAlertWithNoLogin();
            }
            else {

                updateButton.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/client/ManagerProductManagement.fxml"));

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
    }

    @FXML
    void initialize() {
        returnButton.setOnAction(event -> {
            returnButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/client/ManagerProductManagement.fxml"));

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
            return name.getText().equals("") || quantity.getText().equals("") ||
                    cost.getText().equals("");
        }
        catch (Exception e) {
            System.out.println("Error");
            return true;
        }
    }
}
