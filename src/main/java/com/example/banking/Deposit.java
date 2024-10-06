package com.example.banking;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class Deposit {
    private String accountNum;
    private String accountName;
    private String money;
    private double depositMoney;


    @FXML
    private TextField cash;

    @FXML
    private Label label;

    private double balance;
    private Scene previousScene;
    private Stage stage;

    public void setAccountInfo(String accountName, String accountNum, double balances) {
        this.accountName = accountName;
        this.accountNum = accountNum;
        this.balance = balances;
    }



    public void setPreviousScene(Scene previousScene, Stage stage) {
        this.previousScene = previousScene;
        this.stage = stage;
    }

    @FXML
    protected void onConfirmClick(ActionEvent event) throws IOException {  // Add ActionEvent here
       money = cash.getText();
       depositMoney = Double.parseDouble(money);
       if(depositMoney > 0) {
           balance += depositMoney;
           mainMenu.setBalance(this.balance);
           label.setText("You Have Deposited £"+money);
       }
       else {
           label.setText("INCORRECT AMOUNT DEPOSITED");
       }
    }


    @FXML
    protected void goBack(ActionEvent event) {
        if (previousScene != null && stage != null) {
            stage.setScene(previousScene); // Return to main menu
            stage.setTitle("Main Menu");
        }
    }
}

