package com.stacks.java.controllers;

import com.stacks.java.DBConnect;
import com.stacks.java.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.sql.SQLException;

import static com.stacks.java.controllers.Welcome.loguser;

public class Generate {

    @FXML
    private CheckBox cknumbers;


    @FXML
    private Button generate;

    @FXML
    private Button save;

    @FXML
    private TextField showpass;

    @FXML
    private TextField passlength;

    @FXML
    private Button dashboard;

    @FXML
    private TextField acc;

    @FXML
    Parent root;

    @FXML
    void onDashboard(ActionEvent event) {

        try{
            Stage loginStage = Main.stage;
            root = FXMLLoader.load(getClass().getResource("/com/stacks/java/fxml/Dashboard.fxml"));
            loginStage.setScene(new Scene(root));
            loginStage.setResizable(false);
            loginStage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onGenerate(ActionEvent event) {
        String len = passlength.getText();
        if (cknumbers.isSelected()) {
            String string = RandomStringUtils.random(Integer.parseInt(len), true, true);
            showpass.setText(string);
        } else {
            String string = RandomStringUtils.random(Integer.parseInt(len), true, false);
            showpass.setText(string);
        }
    }

    @FXML
    void onSave(ActionEvent event) {
        String account = acc.getText();
        String pass = showpass.getText();

        try {
            String query = String.format("INSERT INTO `passtable` (`username`, `acc`, `pass`) VALUES ('%s', '%s', '%s')",loguser,account,pass);
            DBConnect.getStatement().executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Save the Generated Password



        try{
            Stage loginStage = Main.stage;
            root = FXMLLoader.load(getClass().getResource("/com/stacks/java/fxml/Dashboard.fxml"));
            loginStage.setScene(new Scene(root));
            loginStage.setResizable(false);
            loginStage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
