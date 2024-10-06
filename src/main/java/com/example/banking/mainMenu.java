package com.example.banking;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class mainMenu {
    @FXML
    private Label welcomeText;

    private String accountName;
    private String accountNum;
    private Scene previousScene;
    private Stage stage;

    private static double balance;

    // Set account info and balance
    public void setAccountInfo(String accountName, String accountNum, double balances) {
        this.accountName = accountName;
        this.accountNum = accountNum;
        welcomeText.setText("Welcome " + accountName + " to BADHAN BANK\nAccount Number: " + accountNum);
        setBalance(balances);
    }

    // Set the previous scene and stage for navigation
    public void setPreviousScene(Scene previousScene, Stage stage) {
        this.previousScene = previousScene;
        this.stage = stage;
    }

    // Action to open the deposit page
    @FXML
    protected void onDepositClick(ActionEvent event) throws IOException {
        // Load the deposits.fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("deposits.fxml"));
        Scene newScene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Deposits");

        // Get the controller of the deposit scene
        Deposit depositController = fxmlLoader.getController();


        // Pass the current scene and stage to the deposit controller
        depositController.setPreviousScene(((Node) event.getSource()).getScene(), (Stage) ((Node) event.getSource()).getScene().getWindow());

        // Optionally pass account information and balance to the deposit controller
        depositController.setAccountInfo(accountName, accountNum,balance);

        // Set the new scene
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(newScene);
    }

    // Action to open the balance page
    @FXML
    protected void onBalanceClick(ActionEvent event) throws IOException {
        // Load the Balance.fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Balance.fxml"));
        Scene newScene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Balance");

        // Get the controller of the balance scene
        Balance balanceController = fxmlLoader.getController();

        // Pass the previous scene and stage to the balance controller
        balanceController.setPreviousScene(((Node) event.getSource()).getScene(), (Stage) ((Node) event.getSource()).getScene().getWindow());

        // Optionally pass account information and balance to the balance controller
        balanceController.setBalances(balance);


        // Set the new scene
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(newScene);
    }

    // Action to open the withdrawal page
    @FXML
    protected void onWithdrawalsClick(ActionEvent event) throws IOException {
        // Load the Withdrawals.fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Withdrawals.fxml"));
        Scene newScene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Withdrawals");

        // Get the controller of the withdrawals scene
        Withdrawals withdrawalController = fxmlLoader.getController();

        // Pass the previous scene and stage to the withdrawal controller
        withdrawalController.setPreviousScene(((Node) event.getSource()).getScene(), (Stage) ((Node) event.getSource()).getScene().getWindow());


        // Optionally pass account information and balance to the withdrawal controller
        withdrawalController.setAccountInfo(accountName, accountNum,balance);
        // Set the new scene
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(newScene);
    }

    // Action to go back to the previous scene (login or previous page)
    @FXML
    protected void onQuitClick() {


        if (previousScene != null && stage != null) {
            String currentLn;
            String[] accounts;

            try{
                FileReader fileReader = new FileReader("accounts.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while((currentLn = bufferedReader.readLine())!=null){
                    accounts = currentLn.split(",");
                    if(accountName.equals(accounts[0]) && accountNum.equals(accounts[1])){
                        String stringBalance = Double.toString(balance);
                        accounts[3] = stringBalance;
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            stage.setScene(previousScene);
            stage.setTitle("BADHAN BANK"); // Optionally set the title back to Main Menu

        }
    }

    // Getter and Setter for balance
    public double getBalance() {
        return balance;
    }

    public static void setBalance(double balances) {
        balance = balances;
    }
}
