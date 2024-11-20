package com.languagefluent.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupnarration.models.User;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ProfileController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private ListView<String> progressList;

    @FXML
    private Button saveButton;

    private static final String USER_DATA_FILE = "src/main/resources/data/users.json";

    private User currentUser;

    @FXML
    public void initialize() {
        loadUserData();
        populateProgress();
    }

    private void loadUserData() {
        try {
            // Load the user data from the JSON file (mocking backend for now)
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(USER_DATA_FILE);
            User[] users = mapper.readValue(file, User[].class);

            // Assuming the logged-in user is the first one for demonstration
            currentUser = users[0];

            // Populate fields with user data
            usernameField.setText(currentUser.getUsername());
            emailField.setText(currentUser.getEmail());

        } catch (Exception e) {
            showError("Failed to load user data.");
            e.printStackTrace();
        }
    }

    private void populateProgress() {
        try {
            // Mock progress data (replace with backend integration)
            Map<String, String> progressData = currentUser.getLanguageProgress();
            for (Map.Entry<String, String> entry : progressData.entrySet()) {
                progressList.getItems().add(entry.getKey() + ": " + entry.getValue());
            }
        } catch (Exception e) {
            showError("Failed to load progress data.");
            e.printStackTrace();
        }
    }

    @FXML
    private void saveChanges() {
        try {
            // Update user object with new data
            currentUser.setUsername(usernameField.getText());
            currentUser.setEmail(emailField.getText());

            // Save changes back to the JSON file
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(USER_DATA_FILE);
            User[] users = {currentUser};
            mapper.writeValue(file, users);

            showInfo("Profile updated successfully!");
        } catch (Exception e) {
            showError("Failed to save changes.");
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}