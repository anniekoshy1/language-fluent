package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.languagefluent.App;
import com.model.LanguageLearningFacade;
import com.model.ProjectUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignupController {
    @FXML
    public void btnBackClicked(MouseEvent event) throws IOException{
        App.setRoot("beginning");
    }
}
