package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.languagefluent.App;
import com.model.Course;
import com.model.LanguageLearningFacade;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CourseHomeController implements Initializable {

    @FXML private Label test;
    @FXML private Label courseDescription;
    @FXML private Label courseTitle;
    @FXML private GridPane grid_lessons;
    @FXML private VBox lessonsVBox;
    private LanguageLearningFacade facade;
    private User user;
    private Course currentCourse;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = LanguageLearningFacade.getInstance();
        user = facade.getCurrentUser();
        test.setText(user.getUsername());
    }




    @FXML
    public void languagebasics(MouseEvent event) throws IOException{
        App.setRoot("StoryTelling");
    }

    

    @FXML
    private void onHomeButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("MainHome");
    }

    // @FXML 
    // private void onProfileButtonClicked(MouseEvent event) throws IOException {
    //     App.setRoot("profile");
    // }


}