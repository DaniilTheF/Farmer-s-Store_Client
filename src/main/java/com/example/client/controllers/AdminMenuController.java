package com.example.client.controllers;

import com.example.client.FarmModels.Cart;
import com.example.client.FarmModels.Earned;
import com.example.client.FarmModels.Orders;
import com.example.client.FarmModels.Product;
import com.example.client.Modules.Errors;
import com.example.client.connections.Connect;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.*;

public class AdminMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Button ordersViewButton;
    @FXML
    private Button productsViewButton;
    @FXML
    private Button earnViewButton;
    @FXML
    private Button usersManagementButton;
    @FXML
    private Button returnButton;
    @FXML
    private Button banButton;
    @FXML
    private Button chartButton;

    @FXML
    void userControl(ActionEvent event) {
        usersManagementButton.getScene().getWindow().hide();
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
    @FXML
    void orderView(ActionEvent event) {
        Gson gson = new Gson();
        Connect.connection.sendMessage("ViewOrders");
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
            Type fooType = new TypeToken<ArrayList<Orders>>() {}.getType();
            ArrayList<Orders> r =  gson.fromJson(acc,fooType);
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
    void chart(ActionEvent event) {
        Gson gson = new Gson();
        Connect.connection.sendMessage("ViewProduct");
        String mes = "";
        try {
            mes = Connect.connection.readMessage();
        } catch(IOException ex){
            System.out.println("Error in reading");
        }
        if (mes.equals("No data"))
            Errors.showAlertWithNoProduct();
        else {
            String obj = Connect.connection.readObject().toString();
            Type fooType = new TypeToken<ArrayList<Product>>() {}.getType();
            ArrayList<Product> r =  gson.fromJson(obj,fooType);
            Connect.connection.sendMessage("ViewCart");
            try {
                mes = Connect.connection.readMessage();
            } catch(IOException ex){
                System.out.println("Error in reading");
            }
            if (mes.equals("No data"))
                Errors.showAlertWithNoProduct();
            else {
                String obj1 = Connect.connection.readObject().toString();
                Type fooType1 = new TypeToken<ArrayList<Cart>>() {}.getType();
                ArrayList<Cart> list =  gson.fromJson(obj1,fooType1);
                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
                Map<String,Integer> map = new HashMap<>();
                Stage stage = new Stage();
                Scene scene = new Scene(new Group());
                stage.setTitle("Demand");
                stage.setWidth(500);
                stage.setHeight(500);
                for(Product p: r){
                    map.put(p.getName(),0);
                }
                for(Cart c: list){
                    for (Map.Entry<String, Integer> m: map.entrySet()) {
                        if(m.getKey().equals(c.getProductName())){
                            m.setValue(m.getValue()+c.getAmount());
                        }
                    }
                }
                for(Map.Entry<String, Integer> m: map.entrySet()){
                    pieChartData.add(new PieChart.Data(m.getKey(),m.getValue()));
                }
                final PieChart chart = new PieChart(pieChartData);
                chart.setTitle("Demands For Products");

                ((Group) scene.getRoot()).getChildren().add(chart);
                stage.setScene(scene);
                stage.show();
            }
        }
    }
    @FXML
    void productView(ActionEvent event) {
        Gson gson = new Gson();
        Connect.connection.sendMessage("ViewProduct");
        String mes = "";
        try {
            mes = Connect.connection.readMessage();
        } catch(IOException ex){
            System.out.println("Error in reading");
        }
        if (mes.equals("No data"))
            Errors.showAlertWithNoProduct();
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
    void earnedView(ActionEvent event) {
        Gson gson = new Gson();
        Connect.connection.sendMessage("ViewEarned");
        String mes = "";
        try {
            mes = Connect.connection.readMessage();
        } catch(IOException ex){
            System.out.println("Error in reading");
        }
        if (mes.equals("No data"))
            Errors.showAlertWithNoEarn();
        else {
            String obj = Connect.connection.readObject().toString();
            Type fooType = new TypeToken<ArrayList<Earned>>() {}.getType();
            ArrayList<Earned> r =  gson.fromJson(obj,fooType);
            ListView<Earned> a = new ListView<>();
            for(Earned p: r){
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
    void banControl(ActionEvent event) {
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

