package com.example.client.controllers;

import com.example.client.FarmModels.Cart;
import com.example.client.FarmModels.Orders;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerOrderAddController {
    Cart cart = new Cart();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addProductButton;

    @FXML
    private Button createOrderButton;

    @FXML
    private TextField name;
    @FXML
    private Button returnButton;

    @FXML
    private TextField amount;

    @FXML
    private Button viewProductButton;
    @FXML
    private Button viewCartButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button editButton;
    private Integer tryParse(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    @FXML
    void returnToOrderMenu(ActionEvent event) {
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
    }

    @FXML
    void initialize() {
        ArrayList<Product> viewlist = new ArrayList<>() ;
        ArrayList<Cart> cartlist = new ArrayList<>();
        Product product = new Product();
        viewProductButton.setOnAction(event -> {
            //заполняем коллекцию продуктами чтобы вывести их пользователю
            //а также проверять корректность ввода товара для покупки
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
                if (viewlist.size() == 0){
                    for (Product p: r) {

                        viewlist.add(p);
                    }
                }
                for(Product p: viewlist){
                    a.getItems().add(p);
                }

                VBox vBox = new VBox(a);

                Scene scene = new Scene(vBox, 1000, 400);

                Stage stage = new Stage();
                stage.setTitle("Out Put Information");
                stage.setScene(scene);
                stage.showAndWait();
            }
        });
        addProductButton.setOnAction(event->{
            //заполняем коллекцию продуктами введенными пользователем
            //проверяем их на существование и наличие того кол-ва, которое хочет купить пользователь
            Cart cart1 = new Cart();
            for (Product p : viewlist) {
                if(p.getName().equals(name.getText()) && p.getQuantity() >= tryParse(amount.getText()) && tryParse(amount.getText())> 0){
                    cart1.setProductName(p.getName());
                    cart1.setProductId(p.getId());
                    cart1.setPersonId(Connect.id);
                    cart1.setAmount(tryParse(amount.getText()));
                    cart1.setPrice(p.getCost()*tryParse(amount.getText()));
                    cart.setTotal_price(cart1.getPrice());
                    cartlist.add(cart1);
                    p.setQuantity(p.getQuantity() - tryParse(amount.getText()));
                }
            }
            if(!(cart1.getAmount() > 0)){
                 Errors.AlertWithIncorrectData();
            }
            name.clear();
            amount.clear();
        });
       /* removeButton.setOnAction(event -> {
           int a = cartlist.size();
            for (Cart c : cartlist) {
                if(c.getProductName().equals(name.getText()) && c.getAmount() == tryParse(amount.getText()) && tryParse(amount.getText())> 0){
                    product.setName(name.getText());
                    product.setQuantity(tryParse(amount.getText()));
                    cartlist.remove(c);
                }
            }
            for (Product p:viewlist){
                if(p.getName().equals(product.getName()) && p.getQuantity() == product.getQuantity())  {
                    p.setQuantity(p.getQuantity() + product.getQuantity());
                }
            }
            if(a == cartlist.size()){
                Errors.AlertWithIncorrectData();
            }
        });*/
       /* editButton.setOnAction(event -> {

            for (Cart c : cartlist) {
                if(c.getProductName().equals(name.getText()) && c.getAmount() >= tryParse(amount.getText()) && tryParse(amount.getText())> 0){

                    c.setAmount(c.getQuantity() - tryParse(amount.getText()));
                }
            }
            if(){
                Errors.AlertWithIncorrectData();
            }
        });*/
        viewCartButton.setOnAction(event->{
            //вывод корзины с добавленными в нее продуктами
            ListView<Cart> c = new ListView<>();
            for (Cart p: cartlist) {
                c.getItems().add(p);
            }
            VBox vBox = new VBox(c);

            Scene scene = new Scene(vBox, 1000, 400);

            Stage stage = new Stage();
            stage.setTitle("Added Product");
            stage.setScene(scene);
            stage.showAndWait();
        });


        createOrderButton.setOnAction(event -> {
            Gson gson = new Gson();
            //отправление конечного массива продуктов на сервер для последующей обработки
            Connect.connection.sendMessage("UpdateProductQuantity");
            Type fooType1 = new TypeToken<ArrayList<Product>>() {}.getType();
            Connect.connection.sendObject(gson.toJson(viewlist,fooType1));
            viewlist.clear();
            Connect.connection.sendMessage("NewOrder");
            cart.setPersonId(Connect.id);
            Connect.connection.sendObject(gson.toJson(cart));
            String mes="";
            try {
                mes = Connect.connection.readMessage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(mes.equals("OK")){
                String obj = Connect.connection.readObject().toString();
                Orders order = gson.fromJson(obj,Orders.class);
                order.setTotal_price(cart.getTotal_price());
                Type fooType = new TypeToken<ArrayList<Cart>>() {}.getType();
                Connect.connection.sendObject(gson.toJson(cartlist, fooType));
                ListView<Orders> c = new ListView<>();
                c.getItems().add(order);

                VBox vBox = new VBox(c);
                Scene scene = new Scene(vBox, 1000, 400);
                Stage stage = new Stage();
                stage.setTitle("Your Order");
                stage.setScene(scene);
                stage.showAndWait();
                cartlist.clear();
            }else{
                Errors.showAlertWithNoData();
            }

        });

    }

}
