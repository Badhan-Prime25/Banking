package com.example.banking;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class loginController {
    private String accountNum;
    private String accountName;

    @FXML
    private TextField name;

    @FXML
    private TextField accoutNumber;

    private double balances;

    private Stage stage;
    private Scene scene1;
    private java.io.FileReader FileReader;

    public void setAccountInfo(String accountName, String accountNum, double balances) {
        this.accountName = accountName;
        this.accountNum = accountNum;
    }

    @FXML
    protected void onLoginClick(ActionEvent event) throws IOException {  // Add ActionEvent here
        String accountName = name.getText();
        this.accountNum = accoutNumber.getText();
        String currentLn;
        boolean exist = false;
        String[] accounts;
        
        try{
            FileReader fileReader = new FileReader("accounts.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((currentLn = bufferedReader.readLine())!=null){
                accounts = currentLn.split(",");
                if(accounts.length == 3) {
                    if (accountName.equals(accounts[0]) && accountNum.equals(accounts[1])) {
                        exist = true;
                        this.balances = Double.parseDouble(accounts[2]);
                        break;
                    }
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            exist = false;
        }
        
        
        if(!accountName.isEmpty() && !accountNum.isEmpty() && exist) {
            // Load the main menu FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
            Scene newScene = new Scene(fxmlLoader.load(), 320, 240);

            // Get the controller of the new scene
            mainMenu scene2Controller = fxmlLoader.getController();

            // Pass data (account name and account number) to the main menu controller
            scene2Controller.setAccountInfo(accountName, accountNum, balances);

            // Pass the current stage and scene to enable the back navigation
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene2Controller.setPreviousScene(currentStage.getScene(), currentStage);

            // Set the new scene on the same stage
            currentStage.setScene(newScene);
            currentStage.setTitle("Main Menu");
        }
        else{
            onRegisterClick(event);
        }
        accoutNumber.clear();
        name.clear();


    }
    @FXML
    protected void onRegisterClick(ActionEvent event) throws IOException {  // Add ActionEvent here
        String accountName = name.getText();
        this.accountNum = accoutNumber.getText();
            try{
                FileWriter fileWriter = new FileWriter("C:/Users/badha/OneDrive/Documents/Data Structure/Banking/src/main/java/com/example/banking/accounts.txt",true);
                fileWriter.write(accountName + "," + accountNum +", 0 \n");
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        accoutNumber.clear();
        name.clear();


    }
}
