package com.controllers;

import java.io.IOException;

import com.languagefluent.App;
import com.model.Assessment;
import com.model.FlashcardChild;
import com.model.WordsList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class FlashcardController {

    @FXML
    private Label questionLabel; // Label for flashcards.fxml
    @FXML
    private Label fullQuestionLabel; // Label for flashcardsconfidence.fxml

        @FXML
    private Button backToCourseHomeButton;

    private WordsList wordsList;
    private Assessment assessment;
    private FlashcardChild currentFlashcard;

    @FXML
    public void initialize() {
        wordsList = WordsList.getInstance();
        assessment = new Assessment();

        if (questionLabel != null) {
            // Logic for flashcards.fxml
            initializeFlashcardsScreen();
        } else if (fullQuestionLabel != null) {
            // Logic for flashcardsconfidence.fxml
            initializeConfidenceScreen();
        }
    }

    private void initializeFlashcardsScreen() {
        // Generate a new flashcard question
        currentFlashcard = assessment.generateFlashcardQuestion(wordsList);
        App.setSelectedFlashcard(currentFlashcard);
        questionLabel.setText(currentFlashcard.toString());
    }

    private void initializeConfidenceScreen() {

        currentFlashcard = App.getSelectedFlashcard();
        // Display the full question (word + translation)
        if (currentFlashcard != null) {
            fullQuestionLabel.setText(currentFlashcard.getFullQuestion());
        } else {
            fullQuestionLabel.setText("No flashcard available.");
        }

    }

    @FXML
    private void navigateToFlashcardsConfidence() {
        try {
            App.setSelectedFlashcard(currentFlashcard);
            App.setRoot("flashcardsconfidence");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleConfidenceRating() {
        try {
            App.setRoot("flashcards");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
