package com.stacks.java.controllers;

import com.stacks.java.DBConnect;
import com.stacks.java.Main;
//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
//import jdk.nashorn.internal.objects.Global;

import java.io.IOException;
import java.sql.SQLException;

public class Welcome {
    public static  String loguser;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private Button SETUP;

    @FXML
    private Label statusw;

    @FXML
    private Button exit;

    @FXML
    Parent root;




    @FXML
    public void onLogin() {
        String user = username.getText();
        String pass = password.getText();

        try {
            String query = String.format("SELECT * FROM usertable WHERE username = '%s' and password = '%s'", user, pass);
            boolean correct = DBConnect.getStatement().executeQuery(query).next();

            if (correct) {
                loguser = user;

                try {

                    Stage dashStage = Main.stage;
                    root = FXMLLoader.load(getClass().getResource("/com/stacks/java/fxml/Dashboard.fxml"));
                    dashStage.setScene(new Scene(root));
                    dashStage.setResizable(false);
                    dashStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                // public String account = String.format("SELECT `accID` FROM `usertable` WHERE username = %s and password = %s",user,pass);


            }else
                        {
                            statusw.setText("Incorrect username or password");
                        }
        } catch (SQLException e)
                {
                    e.printStackTrace();
                }
    }


@FXML
    public void onSetup() {
    try{
    Stage registerStage = Main.stage;
    root = FXMLLoader.load(getClass().getResource("/com/stacks/java/fxml/Setup.fxml"));
    registerStage.setScene(new Scene(root));
    registerStage.setResizable(false);
    registerStage.show();
    }catch (IOException e) {
        e.printStackTrace();
        }
    }

    @FXML
    public void onExit(){
        Platform.exit();
        System.exit(0);

    }








}


