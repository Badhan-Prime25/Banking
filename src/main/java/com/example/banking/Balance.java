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

public class Balance {
    private String accountNum;
    private String accountName;
    private double balance;
    private Scene previousScene;
    private Stage stage;

    @FXML
    private Label balanceLabel;

    // Method to set the balance on the balanceLabel


    public void setAccountInfo(String accountName, String accountNum, double balances) {
        this.accountName = accountName;
        this.accountNum = accountNum;
        balance = balances;
    }



    public void setPreviousScene(Scene previousScene, Stage stage) {
        this.previousScene = previousScene;
        this.stage = stage;
    }

    // Method to set the balance on the balanceLabel
    public void setBalances(double balance) {
        balanceLabel.setText("Current Balance: £" + balance);
    }


    @FXML
    protected void goBack(ActionEvent event) {
        if (previousScene != null && stage != null) {
            stage.setScene(previousScene); // Return to main menu
            stage.setTitle("Main Menu");
        }
    }

}