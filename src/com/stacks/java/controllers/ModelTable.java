package com.stacks.java.controllers;


import javafx.beans.property.SimpleStringProperty;


public class ModelTable {

    public SimpleStringProperty accounts = new SimpleStringProperty();
    public SimpleStringProperty passwords = new SimpleStringProperty();


    public String getAccounts() {
        return accounts.get();
    }

    public SimpleStringProperty accounts() {
        return accounts;
    }

    public void setAccounts(String accounts) {
        this.accounts.set(accounts);
    }

    public String getPasswords() {
        return passwords.get();
    }

    public SimpleStringProperty passwordsProperty() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords.set(passwords);
    }
}
