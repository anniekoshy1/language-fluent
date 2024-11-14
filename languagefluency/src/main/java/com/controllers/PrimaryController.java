package com.controllers;

import java.io.IOException;

import com.languagefluent.App;

import javafx.fxml.FXML;

import com.languagefluent.App;

import com.narration.*;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        Narriator.playSound("Bonjour, nous sommes les quatre mousquetaires!");
        //App.setRoot("secondary");
    }
}
