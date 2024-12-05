package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.languagefluent.App;
import com.model.LanguageLearningFacade;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CourseHomeController implements Initializable {

    @FXML
    public void languagebasics(MouseEvent event) throws IOException{
        App.setRoot("StoryTelling");
    }

    @FXML
    private void onHomeButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("MainHome");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}