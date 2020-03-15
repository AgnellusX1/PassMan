package com.stacks.java.controllers;

import com.stacks.java.DBConnect;
import com.stacks.java.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

import javafx.stage.Stage;

public class Setup {

    @FXML
    private TextField newname;

    @FXML
    private TextField newpass;

    @FXML
    private TextField newpass2;

    @FXML
    private Button create;

    @FXML
    private Button login;

    @FXML
    private Label regstatus;

    @FXML
    Parent root;

    @FXML
    public void onCreate() {
        String username = newname.getText();
        String password = newpass.getText();
        String confpassword = newpass2.getText();

        if (username.isEmpty() || password.isEmpty() || confpassword.isEmpty()) {
            regstatus.setText("Fields cannot be empty");
        } else if (!password.equals(confpassword)) {
            regstatus.setText("Passwords do not match");
        } else if (doesUserExist(username)) {
            regstatus.setText("User already exists");
        } else {
            registerUser(username, password);
        }

    }

    @FXML
    public void onLogin() {
        try{
        Stage loginStage = Main.stage;
        root = FXMLLoader.load(getClass().getResource("/com/stacks/java/fxml/Welcome.fxml"));
        loginStage.setScene(new Scene(root));
        loginStage.setResizable(false);
        loginStage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void registerUser(String username, String password) {
        try {
            String insert = String.format("INSERT INTO `usertable`(`username`, `password`) VALUES ('%s','%s')", username, password);
            DBConnect.getStatement().executeUpdate(insert);
            regstatus.setText("Registration successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    boolean doesUserExist(String username) {
        boolean found = false;
        try {
            String query = String.format("SELECT * FROM usertable WHERE username = '%s';", username);
            found = DBConnect.getStatement().executeQuery(query).next();

//
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }


}