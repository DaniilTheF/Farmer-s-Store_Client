package com.example.client.controllers;

import com.example.client.FarmModels.Person;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logInButton;

    @FXML
    private TextField logInField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    void authorization(ActionEvent event) throws IOException {
        Gson gson = new Gson();
        if (checkInput())
            Errors.showAlertWithNullInput();
        else {
            Connect.connection.sendMessage("LogIn");
            Person auth = new Person();
            auth.setLogin(logInField.getText());
            auth.setPassword(passwordField.getText());
            Connect.connection.sendObject(gson.toJson(auth));

            String mes = "";
            try {
                mes = Connect.connection.readMessage();
            } catch (IOException ex) {
                System.out.println("Error in reading");
            }
            if (mes.equals("There is no data!"))
                Errors.showAlertWithNoLogin();
            else {
                String acc = Connect.connection.readObject().toString();
                Person p = gson.fromJson(acc,Person.class);
                System.out.println("чтение ответа от сервера");
                Connect.id = p.getId();
                Connect.role = p.getRole();
                Connect.banned = p.isBanned();
                System.out.println(Connect.role);
                FXMLLoader loader = new FXMLLoader();

                if (Objects.equals(Connect.role, "admin")) {
                    logInButton.getScene().getWindow().hide();
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
                }
                else if(Objects.equals(Connect.role, "manager") && !Connect.banned){ loader.setLocation(getClass().getResource("/com/example/client/ManagerMenu.fxml"));
                    logInButton.getScene().getWindow().hide();
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

                else if(Objects.equals(Connect.role, "customer") && !Connect.banned){loader.setLocation(getClass().getResource("/com/example/client/CustomerMenu.fxml"));
                    logInButton.getScene().getWindow().hide();
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
                else{
                    Errors.UareBanned();
                }
            }
        }
    }
    @FXML
    void initialize() {
        signUpButton.setOnAction(event -> {
            signUpButton.getScene().getWindow().hide();
            Connect.connection.sendMessage("SignUp");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/client/SignUp.fxml"));
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
            return logInField.getText().equals("") || passwordField.getText().equals("");
        }
        catch (Exception e) {
            System.out.println("Error");
            return true;
        }
    }

}
