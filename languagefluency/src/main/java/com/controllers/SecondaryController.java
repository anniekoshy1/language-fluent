package com.controllers;

import java.io.IOException;

import com.languagefluent.App;

import javafx.fxml.FXML;

import com.languagefluent.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}