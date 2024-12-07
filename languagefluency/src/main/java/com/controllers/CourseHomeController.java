package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.languagefluent.App;
import com.model.Course;
import com.model.FlashcardQuestion;
import com.model.LanguageLearningFacade;
import com.model.Lesson;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class CourseHomeController implements Initializable {
        
    @FXML private Label test;
    @FXML private Label courseDescription;
    @FXML private Label courseTitle;
    @FXML private VBox flashcardsVBox;
    @FXML private VBox lessonsVBox;
    @FXML private Label courseProgress;

    
    private LanguageLearningFacade facade;
    private User user;
    private Course currentCourse;
    private FlashcardQuestion flashcards;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = LanguageLearningFacade.getInstance();
        user = facade.getCurrentUser();
        
        currentCourse = facade.getCurrentCourseDetails();
        refreshCourseData();

        courseProgress.setText(String.format("Course Progress: %.2f%%", currentCourse.getCourseProgress()));

        if (currentCourse != null) {
            // Set course title and description
            courseTitle.setText(currentCourse.getName());
            courseDescription.setText(currentCourse.getDescription());

            // Display lessons
            displayLessons(currentCourse.getAllLessons());
            displayFlashcards(currentCourse.getFlashcards());
        } else {
            courseTitle.setText("Course not found.");
            courseDescription.setText("Please select a valid course.");
        }
    }

        private void displayLessons(ArrayList<Lesson> lessons) {
        lessonsVBox.getChildren().clear(); // Clear any previous data

        for (Lesson lesson : lessons) {
            VBox lessonBox = new VBox();
            lessonBox.setSpacing(10);
            lessonBox.setStyle("-fx-padding: 10; -fx-border-color: lightgray;");

            Label lessonName = new Label(lesson.getLessonName());
            lessonName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            lessonBox.setOnMouseClicked(event -> handleLessonClick(lesson));
            Label lessonProgress = new Label("Progress: " + lesson.getLessonProgress() + "%");


            lessonBox.getChildren().addAll(lessonName, lessonProgress);
            lessonsVBox.getChildren().add(lessonBox);
        }
    }

        private void displayFlashcards(ArrayList<FlashcardQuestion> flashcards) {
        flashcardsVBox.getChildren().clear(); // Clear existing flashcard nodes

        for (FlashcardQuestion flashcard : flashcards) {
            VBox flashcardBox = new VBox();
            flashcardBox.setSpacing(10);
            flashcardBox.setStyle("-fx-padding: 10; -fx-border-color: lightgray;");

            flashcardBox.setOnMouseClicked(event -> handleFlashcardClick(flashcard));

            Label flashcardName = new Label(flashcard.getFlashcardName());
            flashcardName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            Label flashcardProgress = new Label("Progress: " + flashcard.getFlashcardProgress() + "%");

            flashcardBox.getChildren().addAll(flashcardName, flashcardProgress);
            flashcardsVBox.getChildren().add(flashcardBox);
        }
    }

    private void refreshCourseData() {
    currentCourse = facade.getCurrentCourseDetails(); // Reload the current course details
    if (currentCourse != null) {
        // Update course title and description
        courseTitle.setText(currentCourse.getName());
        courseDescription.setText(currentCourse.getDescription());

        // Update the displayed lessons and flashcards
        displayLessons(currentCourse.getAllLessons());
        displayFlashcards(currentCourse.getFlashcards());
    } else {
        courseTitle.setText("Course not found.");
        courseDescription.setText("Please select a valid course.");
    }
}

    @FXML
    private void handleLessonClick(Lesson lesson){
        try {
            System.out.println("Clicked on lesson: " + lesson.getLessonName());
            App.setRoot("StoryTelling");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleFlashcardClick(FlashcardQuestion flashcard) {
        try {
            System.out.println("Clicked on flashcard: " + flashcard.getFlashcardName());
            App.setRoot("flashcards");
        } catch (IOException e) {
            e.printStackTrace();
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

    @FXML
    private void onAssessmentButtonClicked(MouseEvent event) throws IOException {
        App.setRoot("assessment1");
    }
    @FXML
    private void onAssessment2ButtonClicked(MouseEvent event) throws IOException {
        App.setRoot("assessment2");
    }




}