package com.example.client.controllers;

import com.example.client.FarmModels.Cart;
import com.example.client.FarmModels.Orders;
import com.example.client.FarmModels.Person;
import com.example.client.FarmModels.Product;
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
import java.util.Vector;

public class BanControlController {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button banButton;

        @FXML
        private Button unBanButton;
        @FXML
        private Button viewBannedButton;
        @FXML
        private Button returnButton;

        @FXML
        void ban(ActionEvent event) {
            banButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/client/BanControlBan.fxml"));

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
        void unBan(ActionEvent event) {
            unBanButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/client/BanControlUnBan.fxml"));

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
    void viewBanned(ActionEvent event) {
        Gson gson = new Gson();
        Connect.connection.sendMessage("ViewBanned");
        String mes = "";
        try {
            mes = Connect.connection.readMessage();
        } catch(IOException ex){
            System.out.println("Error in reading");
        }
        if (mes.equals("No data"))
            Errors.alertWithNoBanned();
        else {
            String acc = Connect.connection.readObject().toString();
            Type fooType = new TypeToken<ArrayList<Person>>() {}.getType();
            ArrayList<Person> r =  gson.fromJson(acc,fooType);
            ListView<Person> a = new ListView<>();
            for (Person p: r) {
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
        void initialize() {
            returnButton.setOnAction(event -> {
                returnButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/client/AdminMenu.fxml"));

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