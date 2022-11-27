package com.example.client.controllers;

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

public class CustomerMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ordersManagementButton;

    @FXML
    private Button returnButton;

    @FXML
    private Button viewProductsButton;

    @FXML
    private Button paymentButton;

    @FXML
    void ordersManagement(ActionEvent event) {
        ordersManagementButton.getScene().getWindow().hide();
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
}
    @FXML
    void viewProducts(ActionEvent event) {
        Gson gson = new Gson();

        Connect.connection.sendMessage("ViewProduct");
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
            Type fooType = new TypeToken<ArrayList<Product>>() {}.getType();
            ArrayList<Product> r =  gson.fromJson(obj,fooType);
            ListView<Product> a = new ListView<>();
            for(Product p: r){
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
    void paymentManagement(ActionEvent event) {
        paymentButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/client/CustomerPayment.fxml"));

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
     });
    }

}
