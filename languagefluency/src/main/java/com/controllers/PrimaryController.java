package com.controllers;

import java.io.IOException;

import com.languagefluent.App;
import com.model.*;
import com.narration.Narriator;

import javafx.fxml.FXML;

import com.languagefluent.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        Narriator.playSound("Hola Mundo");
        //App.setRoot("secondary");
    }
}
