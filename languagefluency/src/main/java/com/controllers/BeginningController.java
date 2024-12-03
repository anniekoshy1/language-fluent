package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.ProjectUI;
import com.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class BeginningController implements Initializable {

    @FXML
    private Text welcomeMessage;

    private User loggedInUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadLoggedInUser();
            if (loggedInUser != null) {
                welcomeMessage.setText("Welcome, " + loggedInUser.getUsername() + "!");
            } else {
                welcomeMessage.setText("Welcome to Language Fluent!");
            }
        } catch (Exception e) {
            showError("Failed to load user data. Please restart.");
        }
    }

    @FXML
    private void onLoginClicked(ActionEvent event) throws IOException {
        try {
            ProjectUI.getInstance().setRoot("login");
        } catch (IOException e) {
            showError("Failed to load.");
            e.printStackTrace();
        }
    }

    @FXML
    private void onSignupClicked(ActionEvent event) throws IOException {
        try {
            ProjectUI.getInstance().setRoot("signup");
        } catch (IOException e) {
            showError("Failed to load.");
            e.printStackTrace();
        }
    }

    private void loadLoggedInUser() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            User[] users = mapper.readValue(
                    getClass().getClassLoader().getResourceAsStream("data/users.json"),
                    User[].class);

            loggedInUser = users[0];
        } catch (Exception e) {
            loggedInUser = null;
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}