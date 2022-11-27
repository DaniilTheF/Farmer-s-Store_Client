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

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private TextField name;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Button returnButton;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField surname;

    @FXML
    void signUpCustomer(ActionEvent event) {
        Gson gson = new Gson();
        String mes = "";
        try {
            mes = Connect.connection.readMessage();
        } catch(IOException ex){
            System.out.println("Error in reading");
        }
        if(!mes.equals("true")){
            if (checkInput()){Errors.showAlertWithNullInput();}
            else {
                System.out.println("Start enter");
                Person admin = new Person();
                admin.setName(name.getText());
                admin.setSurname(surname.getText());
                admin.setPhone(phoneNumber.getText());
                admin.setLogin(loginField.getText());
                if (!passwordField.getText().equals(passwordField2.getText())){
                    Errors.IncorrectPass();
                }else{admin.setPassword(passwordField.getText());
                    admin.setRole("admin");
                    admin.setBanned(false);
                    Connect.connection.sendObject(gson.toJson(admin));
                    System.out.println("Запись отправлена");
                    try {
                        mes = Connect.connection.readMessage();
                    } catch(IOException ex){
                        System.out.println("Error in reading");
                    }
                    if (mes.equals("This user is already existed"))
                        Errors.showAlertWithExistLogin();
                    else {
                        String acc = Connect.connection.readObject().toString();
                        Person r = gson.fromJson(acc,Person.class);
                        Connect.id = r.getId();
                        Connect.role = r.getRole();
                        signUpButton.getScene().getWindow().hide();

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
                    }
                }
            }

        }else{

            if (checkInput())
                Errors.showAlertWithNullInput();
            else {
                Person customer = new Person();
                customer.setName(name.getText());
                customer.setSurname(surname.getText());
                customer.setPhone(phoneNumber.getText());
                customer.setLogin(loginField.getText());
                if (!passwordField.getText().equals(passwordField2.getText())) {
                    Errors.IncorrectPass();
                } else {
                    customer.setPassword(passwordField.getText());
                    customer.setRole("customer");
                    customer.setBanned(false);
                    System.out.println(customer);
                    Connect.connection.sendObject(gson.toJson(customer));
                    System.out.println("Запись отправлена");
                    try {
                        mes = Connect.connection.readMessage();
                    } catch (IOException ex) {
                        System.out.println("Error in reading");
                    }
                    if (mes.equals("This user is already existed"))
                        Errors.showAlertWithExistLogin();
                    else {
                        try {
                            mes = Connect.connection.readMessage();
                        } catch (IOException ex) {
                            System.out.println("Error in reading");
                        }
                        if (mes.equals("This user is already existed"))
                            Errors.showAlertWithExistLogin();
                        else {
                            String acc = Connect.connection.readObject().toString();
                            Person r = gson.fromJson(acc, Person.class);
                            Connect.id = r.getId();
                            Connect.role = r.getRole();
                            signUpButton.getScene().getWindow().hide();

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
                        }
                    }
                }
            }
        }

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
    @FXML
    void returnToMain(ActionEvent event) {
        returnButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/client/hello-view.fxml"));

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
    }

}

