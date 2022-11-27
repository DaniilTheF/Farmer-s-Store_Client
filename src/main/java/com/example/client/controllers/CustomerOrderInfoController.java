package com.example.client.controllers;

import com.example.client.FarmModels.Cart;
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

public class CustomerOrderInfoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ID;

    @FXML
    private Button infoButton;

    @FXML
    private Button returnButton;
    private Integer tryParse2(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @FXML
    void infoAboutOrder(ActionEvent event) {
        Gson gson = new Gson();
        String mes = "";
        if (checkInput()) {
            Errors.showAlertWithNullInput();
        } else {
            Cart cart = new Cart();
            cart.setOrdersId(tryParse2(ID.getText()));
            cart.setPersonId(Connect.id);
            Connect.connection.sendMessage("InfoOrder");
            Connect.connection.sendObject(gson.toJson(cart));
            System.out.println("Запись отправлена");
            try {
                mes = Connect.connection.readMessage();
            } catch (IOException ex) {
                System.out.println("Error in reading");
            }
            if (!mes.equals("OK")){
                Errors.alertWithNoOrders();
            }
            else {
                String obj = Connect.connection.readObject().toString();
                Type fooType = new TypeToken<ArrayList<Cart>>() {}.getType();
                ArrayList<Cart> r =  gson.fromJson(obj,fooType);
                ListView<Cart> a = new ListView<>();
                for (Cart c: r){
                    a.getItems().add(c);
                }
                VBox vBox = new VBox(a);
                Scene scene = new Scene(vBox, 1000, 400);
                Stage stage = new Stage();
                stage.setTitle("Out Put Information");
                stage.setScene(scene);
                stage.showAndWait();
            }
        }
    }

    @FXML
    void initialize() {
        returnButton.setOnAction(event -> {
            returnButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/client/CustomerOrder.fxml"));

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
