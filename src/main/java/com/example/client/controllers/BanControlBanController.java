package com.example.client.controllers;

import com.example.client.FarmModels.Person;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BanControlBanController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ID;

    @FXML
    private Button banButton;
    @FXML
    private Button viewAllButton;

    @FXML
    private Button returnButton;

    @FXML
    void banCustomer(ActionEvent event) {
        Gson gson = new Gson();
        String mes = "";
        if (checkInput()) {
            Errors.showAlertWithNullInput();
        } else {
            System.out.println("Start enter");
            Person customer = new Person();
            customer.setId(Integer.parseInt(ID.getText()));
            Connect.connection.sendMessage("Ban");
            Connect.connection.sendObject(gson.toJson(customer));
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
                banButton.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/client/BanControl.fxml"));

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
    void viewAll(ActionEvent event) {
        Gson gson = new Gson();
        Connect.connection.sendMessage("ViewPersons");
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
            loader.setLocation(getClass().getResource("/com/example/client/BanControl.fxml"));

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
