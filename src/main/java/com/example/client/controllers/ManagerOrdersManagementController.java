package com.example.client.controllers;

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

public class ManagerOrdersManagementController {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button viewOrdersButton;

        @FXML
        private Button sentOrderButton;

        @FXML
        private Button returnButton;

        @FXML
        void viewOrders(ActionEvent event) {
            Gson gson = new Gson();
            Connect.connection.sendMessage("ViewPurchases");
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
        void sentOrder(ActionEvent event) {
            sentOrderButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/client/ManagerOrdersManagementSent.fxml"));

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
                loader.setLocation(getClass().getResource("/com/example/client/ManagerMenu.fxml"));

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
