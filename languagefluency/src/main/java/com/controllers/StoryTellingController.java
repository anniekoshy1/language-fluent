package com.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.languagefluent.App;
import com.model.Course;
import com.model.LanguageLearningFacade;
import com.model.Lesson;
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
    private Button backToCourseHomeButton;

    @FXML 
    private Label storyLabel;

    private String spanishContent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LanguageLearningFacade facade = LanguageLearningFacade.getInstance();
        Course currentCourse = facade.getCurrentCourseDetails();

        if (currentCourse != null && !currentCourse.getAllLessons().isEmpty()) {
            Lesson currentLesson = currentCourse.getAllLessons().get(0); // Assuming the first lesson is used
            spanishContent = currentLesson.getSpanishContent();
        } else {
            spanishContent = null; // No valid content available
        }

        storyLabel.setText("Click play to hear the story.");
    }

    @FXML
    public void onPlayButtonClicked() {
        if (spanishContent != null && !spanishContent.isEmpty()) {
            storyLabel.setText(spanishContent);
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
@FXML
    private void onHomeButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("MainHome");
    }

    @FXML
    private void onProfileButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("profile");
    }
    @FXML
    private void onLogoutButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("beginning");
    }


}