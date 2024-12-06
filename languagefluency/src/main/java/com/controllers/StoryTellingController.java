package com.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.languagefluent.App;
import com.narration.Narriator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


public class StoryTellingController implements Initializable {

    @FXML 
    private Button playButton;

    @FXML
    private Button submitButton;

    @FXML 
    private Label storyLabel;

    @FXML
private Button backToCourseHomeButton;


    private String spanishContent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadStoryFromJSON();
    }

    private void loadStoryFromJSON() {
        spanishContent = "test";
    }

    @FXML
    public void onPlayButtonClicked() {
        if (spanishContent != null && !spanishContent.isEmpty()) {
            readStoryAloud(spanishContent);
        }
    }

    private void readStoryAloud(String content) {
        Narriator.playSound(content);
    }

    @FXML
public void onBackToCourseHomeClicked(MouseEvent event) {
    try {
        // Navigate back to CourseHome
        App.setRoot("CourseHome");
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Error switching to CourseHome.fxml");
    }
}


}