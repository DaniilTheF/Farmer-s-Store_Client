package com.example.client.controllers;

import com.example.client.FarmModels.Cart;
import com.example.client.FarmModels.Orders;
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
import java.util.ResourceBundle;
import java.util.Vector;

public class CustomerOrderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addOrderButton;

    @FXML
    private Button deleteOrderButton;

    @FXML
    private Button returnButton;

    @FXML
    private Button infoAboutOrderButton;

    @FXML
    private Button viewAllOrderButton;

    @FXML
    void addOrder(ActionEvent event) {
        addOrderButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/client/CustomerOrderAdd.fxml"));

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
    void deleteOrder(ActionEvent event) {
        addOrderButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/client/CustomerOrderDelete.fxml"));

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
    void infoAboutOrder(ActionEvent event) {
        infoAboutOrderButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/client/CustomerOrderInfo.fxml"));

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
    void viewAllOrder(ActionEvent event) {
        Gson gson = new Gson();
        Connect.connection.sendMessage("ViewOrder");
        Cart cart = new Cart();
        cart.setPersonId(Connect.id);
        Connect.connection.sendObject(gson.toJson(cart));
        String mes = "";
        try {
            mes = Connect.connection.readMessage();
        } catch(IOException ex){
            System.out.println("Error in reading");
        }
        if (mes.equals("No data"))
            Errors.alertWithNoOrders();
        else {
            String acc = Connect.connection.readObject().toString();
            Type fooType = new TypeToken<Vector<Orders>>() {}.getType();
            Vector<Orders> r =  gson.fromJson(acc,fooType);
            ListView<Orders> a = new ListView<>();
            for (Orders p: r) {
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
        });
    }

}
