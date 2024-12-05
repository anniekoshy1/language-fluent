package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.languagefluent.App;
import com.model.LanguageLearningFacade;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignupController implements Initializable{

    @FXML
    private TextField txt_username2;
    @FXML
    private TextField txt_password2;
    @FXML
    private TextField txt_email2;
    @FXML
    private ChoiceBox<String> txt_Language;
    @FXML 
    private Label Error;

    @FXML
    private void btnSignupClicked(MouseEvent event) throws IOException{
        String username = txt_username2.getText();
        String password = txt_password2.getText();
        String email = txt_email2.getText();

        LanguageLearningFacade facade = LanguageLearningFacade.getInstance();

        if (!facade.registerUser(username, email, password)) {
            Error.setVisible(true);
            return;
        }

        App.setRoot("MainHome");
    }

    @FXML
    public void btnBackClicked(MouseEvent event) throws IOException{
        App.setRoot("beginning");
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_Language.setItems(FXCollections.observableArrayList("Spanish", "French"));
        txt_Language.setValue("Spanish");
        Error.setVisible(false);
    }
}
