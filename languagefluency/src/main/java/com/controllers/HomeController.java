package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.model.ProjectUI;

public class HomeController implements Initializable {

    @FXML
    private void onLoginClicked(ActionEvent event) throws IOException {
        ProjectUI.getInstance().setRoot("login");
    }

    @FXML
    private void onSignupClicked(ActionEvent event) throws IOException {
        ProjectUI.getInstance().setRoot("signup");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}