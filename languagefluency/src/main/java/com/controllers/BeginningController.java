package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.languagefluent.App;
import com.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class BeginningController implements Initializable {

    @FXML
    private Text welcomeMessage;

    private User loggedInUser;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void btnLoginClicked(ActionEvent event) throws IOException{
        App.setRoot("login");
    }
    @FXML
    public void btnSignupClicked(ActionEvent event) throws IOException{
        App.setRoot("signup");
    }
}
