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

public class Withdrawals {
    private String accountNum;
    private String accountName;
    private String money;
    private double withdrawnMoney;
    private double balance;

    @FXML
    private TextField cash;

    @FXML
    private Label label;

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
        withdrawnMoney = Double.parseDouble(money);
        if(withdrawnMoney > 0 && withdrawnMoney <= balance) {
            balance = balance - withdrawnMoney;
            mainMenu.setBalance(this.balance);
            label.setText("You Have Withdrawn £" + withdrawnMoney);
        }
        else{
            label.setText("INSUFFICIENT FUNDS");
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