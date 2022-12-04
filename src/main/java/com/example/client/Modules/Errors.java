package com.example.client.Modules;

import javafx.scene.control.Alert;

public class Errors {
    static public void showAlertWithNullInput(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Ввод данных");
        alert.setContentText("Заполните пустые поля");
        alert.showAndWait();
    }
    static public void alertWithNoMoney(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Недостаточно средств на балансе");
        alert.setContentText("Пополните счет");
        alert.showAndWait();
    }
    static public void isExist(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Заказ уже отправлен");
        alert.setContentText("Укажите другой");
        alert.showAndWait();
    }
    static public void UareBanned(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Нет доступа");
        alert.setContentText("Вы были забанены");
        alert.showAndWait();
    }
    static public void alertPurchasedOrder(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Удаление купленного заказа");
        alert.setContentText("Вы не можете удалить купленный заказ");
        alert.showAndWait();
    }
    static public void alertWithNoOrders(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Нет данных");
        alert.setContentText("Сначала добавьте заказ");
        alert.showAndWait();
    }
    static public void alertWithNoBanned(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Нет данных");
        alert.setContentText("Сначала забаньте пользователя");
        alert.showAndWait();
    }
    static public void IncorrectPass(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Пароли не савподают");
        alert.setContentText("Повторите ввод");
        alert.showAndWait();
    }
    static public void AlertWithIncorrectData(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Не совпадают имя или кол-во продукции");
        alert.setContentText("Повторите ввод");
        alert.showAndWait();
    }
    static public void AlertWithIncorrectOrderID(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Введите свой ID заказа");
        alert.setContentText("Повторите ввод");
        alert.showAndWait();
    }
    static public void showAlertWithExistLogin(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Регистрация");
        alert.setContentText("Такой пользователь уже существует");
        alert.showAndWait();
    }
    static public void showAlertWithNoData(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Нет данных");
        alert.setContentText("Сначала добавьте покупателя");
        alert.showAndWait();
    }
    static public void showAlertWithNoEarn(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Нет данных");
        alert.setContentText("Вы еще ничего не заработали");
        alert.showAndWait();
    }
    static public void showAlertWithNoProduct(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Нет данных");
        alert.setContentText("Вся продукция была распроданна");
        alert.showAndWait();
    }

    static public void showAlertWithNoLogin(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Введите правильно логин или пароль");
        alert.setContentText("Такой пользователь не найден в системе");
        alert.showAndWait();
    }

    static public void showAlertWithData(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Неверно введены данные");
        alert.setContentText("Введите число");
        alert.showAndWait();
    }

    static public void correctOperation(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Correct");
        alert.setHeaderText("");
        alert.setContentText("Операция прошла успешно");
        alert.showAndWait();
    }
}
