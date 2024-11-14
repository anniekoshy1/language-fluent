package com.controllers;

import java.io.IOException;

import com.languagefluent.App;

import javafx.fxml.FXML;

import com.languagefluent.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
