package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.languagefluent.App;
import com.model.LanguageLearningFacade;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


public class ProfileController implements Initializable {
    
    @FXML private Label dynamicUser;
    @FXML private Label dynamicPassword;
    @FXML private Label dynamicEmail;
    @FXML private Label dynamicLanguage;
    @FXML private Label dynamicCourse;

    @FXML
    private Button profileButton;

    private LanguageLearningFacade facade;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = LanguageLearningFacade.getInstance();
        user = facade.getCurrentUser();

        if (user != null) {
            dynamicUser.setText(user.getUsername());
            dynamicPassword.setText(user.getPassword());
            dynamicEmail.setText(user.getEmail());
            dynamicLanguage.setText(user.getCurrentLanguageName());
            dynamicCourse.setText(user.getCurrentCourse().toString());
        } else {
            dynamicUser.setText("User not found.");
            dynamicPassword.setText("No password available.");
            dynamicEmail.setText("No Email available.");
            dynamicLanguage.setText("No Language available");
            dynamicCourse.setText("No Course available");
        }
    }

@FXML
    private void onHomeButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("MainHome");
    }

    @FXML
    private void onLogoutButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("beginning");
    }

}