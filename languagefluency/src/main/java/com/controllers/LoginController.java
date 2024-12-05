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


public class LoginController implements Initializable {
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private Label lbl_error;

    @FXML
    private void btnLoginClicked(MouseEvent event) throws IOException {
        String username = txt_username.getText();
        String password = txt_password.getText();

        LanguageLearningFacade facade = LanguageLearningFacade.getInstance();

        if (!facade.login(username, password)) {
            lbl_error.setText("Invalid login credentials.");
            return;
        }

        ProjectUI.getInstance().setRoot("user_home");
    }

    @FXML
    public void btnBackClicked(MouseEvent event) throws IOException{
        App.setRoot("beginning");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
