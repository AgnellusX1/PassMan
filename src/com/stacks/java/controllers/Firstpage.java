package com.stacks.java.controllers;

import com.stacks.java.DBConnect;
import com.stacks.java.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Popup;
//import javafx.stage.PopupBuilder;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

public class Firstpage {

    @FXML
    private Button credit;

    @FXML
    private Button exit;

    @FXML
   private Button start;

    @FXML
    private Button help;


    @FXML
    Parent root;

    @FXML
    public void initialize() {
        System.out.println("onLoad");
    }

    @FXML
    void onCredits(ActionEvent event) {




        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Credits");
        alert.setHeaderText("JAVA PROJECT - PASSWORD GENERATOR AND MANAGER");
        alert.setContentText("TEAM MEMBERS\nAgnellus Fernandes--Programming and Design\nAnisha Fernandes--Research and Motivation\nClarice D'Silva--Documentation and Research");
        alert.showAndWait();

    }

    @FXML
    void onExit(ActionEvent event) {

        Platform.exit();
        System.exit(0);

    }

    @FXML
    void onHelp(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("JAVA PROJECT - PASSWORD GENERATOR AND MANAGER");
        alert.setContentText("1.Sign-up in The Application To Create a Unique Account\n2.Login to your account\n3.Generate a new Password by clicking on Generate\n4.Chose the options provided and Enter the necessary details to generate your password\n5.Save the password in the database and log out");
        alert.showAndWait();

    }

    @FXML
    void onStart() {
        try {
            Stage primaryStage = Main.stage;
            root = FXMLLoader.load(getClass().getResource("/com/stacks/java/fxml/Welcome.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}