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
import java.security.Key;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
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
    void onSave(ActionEvent event) throws SQLException {

        String account = acc.getText();
        String pass = showpass.getText();
        String query = String.format("SELECT `password` FROM `usertable` WHERE `username`='%s'", loguser);
        ResultSet rs = DBConnect.statement.executeQuery(query);
            String pass1=null;
            if(rs.next()) {
                 pass1 = rs.getString(1);
            }
            System.out.println(pass1);
        String ens=null;
        try{
            SecretKeySpec secretKey;
            byte[] key;
            MessageDigest sha;
            key = pass1.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            ens= Base64.getEncoder().encodeToString(cipher.doFinal(pass.getBytes("UTF-8")));
            System.out.println(ens);
        }
        catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }







        try {
            String qry = String.format("INSERT INTO `passtable` (`username`, `acc`, `pass`) VALUES ('%s', '%s', '%s')", loguser, account, ens);
            DBConnect.getStatement().executeUpdate(qry);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Save the Generated Password


        try {
            Stage loginStage = Main.stage;
            root = FXMLLoader.load(getClass().getResource("/com/stacks/java/fxml/Dashboard.fxml"));
            loginStage.setScene(new Scene(root));
            loginStage.setResizable(false);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
