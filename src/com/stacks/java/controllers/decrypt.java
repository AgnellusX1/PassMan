package com.stacks.java.controllers;
import com.stacks.java.DBConnect;
import com.stacks.java.Main;
//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;

import static com.stacks.java.controllers.decrypt.*;
import static com.stacks.java.controllers.Welcome.loguser;
public class decrypt {
    @FXML
    private TextField enteraccname;
    @FXML
    private TextField decryptpassd;

    @FXML
    private PasswordField decryptpass;

    @FXML
    private Button backd;

    @FXML
    private Button decryptd;

    @FXML
    private Label decryptpage;


    @FXML
    Parent root;

    @FXML
    public void onbackd(){
        try{
            Stage registerStage = Main.stage;
            root = FXMLLoader.load(getClass().getResource("/com/stacks/java/fxml/Dashboard.fxml"));
            registerStage.setScene(new Scene(root));
            registerStage.setResizable(false);
            registerStage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ondecryptd(ActionEvent actionEvent) throws SQLException {
        String accn = enteraccname.getText();
        String pass2 = decryptpass.getText();
        String query = String.format("SELECT `pass` FROM `passtable` WHERE `username`='%s' and `acc`='%s'", loguser, accn);
        ResultSet rs = DBConnect.statement.executeQuery(query);
        String orgp = null;
        if (rs.next()) {
            orgp = rs.getString(1);
        }
        String es = null;
        try {
            SecretKeySpec secretKey;
            byte[] key;
            MessageDigest sha;
            key = pass2.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            es = new String(cipher.doFinal(Base64.getDecoder().decode(orgp)));
            decryptpassd.setText(es);
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }

    }


    }

