package com.example.client.controllers;

import com.example.client.FarmModels.Purchase;
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

public class ManagerOrdersManagementSentController {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TextField ID;

        @FXML
        private Button sentButton;

        @FXML
        private Button returnButton;

        @FXML
        void sentOrder(ActionEvent event) {
            Gson gson = new Gson();
            String mes = "";
            if (checkInput()) {
                Errors.showAlertWithNullInput();
            } else {
                System.out.println("Start enter");
                Purchase orders = new Purchase();
                orders.setId(Integer.parseInt(ID.getText()));
                orders.setCustomerId(Connect.id);
                Connect.connection.sendMessage("Sent");
                Connect.connection.sendObject(gson.toJson(orders));
                System.out.println("Запись отправлена");
                try {
                    mes = Connect.connection.readMessage();
                } catch (IOException ex) {
                    System.out.println("Error in reading");
                }
                if (!mes.equals("OK")) {
                    Errors.alertWithNoOrders();
                } else {
                    try {
                        mes = Connect.connection.readMessage();
                    } catch (IOException ex) {
                        System.out.println("Error in reading");
                    }
                    if (!mes.equals("OK")) {
                        Errors.isExist();
                    } else {
                        sentButton.getScene().getWindow().hide();

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
                return ID.getText().equals("");
            }
            catch (Exception e) {
                System.out.println("Error");
                return true;
            }
        }
    }
