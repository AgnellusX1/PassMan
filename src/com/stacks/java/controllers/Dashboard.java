package com.stacks.java.controllers;
import com.stacks.java.DBConnect;
import com.stacks.java.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import static com.stacks.java.controllers.Welcome.*;

public class Dashboard {
   //Table Skeleton
    @FXML
    private TableView<ModelTable> dashTable;
    @FXML
    private TableColumn<ModelTable, String> acc;
    @FXML
    private TableColumn<ModelTable, String> pass;


    ObservableList<ModelTable> data;

    @FXML
    private Button Create;

    @FXML
    private Button logout;

    @FXML
    private Text welcome ;

    @FXML
    private Label name;


    @FXML
    Parent root;


    @FXML
   private void initialize(){

        acc.setCellValueFactory(new PropertyValueFactory<ModelTable, String>("Accounts"));
        pass.setCellValueFactory(new PropertyValueFactory<ModelTable, String>("Passwords"));

        try {
            buildData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        name.setText(loguser);
   }

    @FXML
    void onCreatenew(ActionEvent event) {
        try {
            Stage registerStage = Main.stage;
            root = FXMLLoader.load(getClass().getResource("/com/stacks/java/fxml/Generate.fxml"));
            registerStage.setScene(new Scene(root));
            registerStage.setResizable(false);
            registerStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onLogout(ActionEvent event) {
        try {
            Stage registerStage = Main.stage;
            root = FXMLLoader.load(getClass().getResource("/com/stacks/java/fxml/Welcome.fxml"));
            registerStage.setScene(new Scene(root));
            registerStage.setResizable(false);
            registerStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    private void buildData() throws SQLException {
        data = FXCollections.observableArrayList();
        String query = String.format("SELECT * FROM `passtable` WHERE `username`='%s'",loguser) ;

        ResultSet rs = DBConnect.statement.executeQuery(query);

        while (rs.next()) {
            ModelTable mt = new ModelTable();
            mt.accounts.set(rs.getString("acc"));
            mt.passwords.set(rs.getString("pass"));
            data.add(mt);
        }
        dashTable.setItems(data);
    }

}
