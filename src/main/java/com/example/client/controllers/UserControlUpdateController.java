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
import java.util.ResourceBundle;

public class UserControlUpdateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private PasswordField idField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField name;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Button returnButton;

    @FXML
    private TextField surname;

    @FXML
    void updateCustomer(ActionEvent event) {
        Gson gson = new Gson();
        String mes = "";
        if (checkInput()) {
            Errors.showAlertWithNullInput();
        } else {
            System.out.println("Start enter");
            Person customer = new Person();
            customer.setId(Integer.parseInt(idField.getText()));
            customer.setName(name.getText());
            customer.setSurname(surname.getText());
            customer.setPhone(phoneNumber.getText());
            customer.setLogin(loginField.getText());
            customer.setPassword(passwordField.getText());
            Connect.connection.sendMessage("UpdatePerson");
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
                idField.clear();
                name.clear();
                surname.clear();
                phoneNumber.clear();
                loginField.clear();
                passwordField.clear();
                addButton.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/client/UserControl.fxml"));

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
            loader.setLocation(getClass().getResource("/com/example/client/UserControl.fxml"));

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
                    return name.getText().equals("") || surname.getText().equals("") ||
                            loginField.getText().equals("") || passwordField.getText().equals("");
                }
                catch (Exception e) {
                    System.out.println("Error");
                    return true;
                }
            }

}
