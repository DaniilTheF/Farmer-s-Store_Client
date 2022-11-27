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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserControlController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addUserButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private Button returnButton;

    @FXML
    private Button updateUserButton;

    @FXML
    private Button viewAllUserButton;

    @FXML
    void addUser(ActionEvent event) {
        addUserButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/client/UserControlAdd.fxml"));

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
    void updateUser(ActionEvent event) {
        updateUserButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/client/UserControlUpdate.fxml"));

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
    void viewAllUser(ActionEvent event) {
        Gson gson = new Gson();
        Connect.connection.sendMessage("ViewPerson");
        String mes = "";
        try {
            mes = Connect.connection.readMessage();
        } catch(IOException ex){
            System.out.println("Error in reading");
        }
        if (mes.equals("No data"))
            Errors.showAlertWithNoData();
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
    void deleteUser(ActionEvent event) {
        deleteUserButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/client/UserControlDelete.fxml"));

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
