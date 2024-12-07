package com.controllers;

import com.model.Assessment;
import com.model.Questions;
import com.model.WordsList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AssessmentController {

    @FXML
    private Label question;  // Label to display the question text

    @FXML
    private VBox optionsBox;  // You can use this if you have multiple options for a question

    @FXML
    private TextField answerField;  // Field where user types their answer

    @FXML
    private Button submitButton;  // Submit button to check the answer

    @FXML
    private Button nextButton;  // Button to move to the next question

    @FXML
    private Label scoreLabel;  // Label to display the score

    @FXML
    private Label resultLabel;  // Label to display the final result after 5 questions

    private Assessment assessment;  // The assessment instance
    private WordsList wordsList;  // Words list to fetch words for questions
    private Questions currentQuestion;  // The current question displayed
    private int currentQuestionIndex = 0;  // Index of the current question
    private int score = 0;  // User's score
    private int totalQuestions = 5;  // Total number of questions in the quiz

    @FXML
    public void initialize() {
        // Initialize the assessment and word list
        wordsList = WordsList.getInstance();
        assessment = new Assessment();

        // Generate the first question
        currentQuestion = assessment.generateRandomQuestion(wordsList);
        question.setText(currentQuestion.toString());  // Set the question text in the label

        // Initialize score
        score = 0;
        resultLabel.setText("");  // Clear result label initially
    }

    // Called when the user clicks the "Submit" button
    @FXML
    public void submitAnswer() {
        String userAnswer = answerField.getText().trim().toLowerCase();  // Get the answer from the text field
        currentQuestion.setUserAnswer(userAnswer);  // Set the user's answer

        if (currentQuestion.isCorrect()) {
            score++;  // Increase score if the answer is correct
        }

        // Update the score label
        scoreLabel.setText("Score: " + score);

        // Optionally, disable the submit button after submission until next question
        submitButton.setDisable(true);
        nextButton.setDisable(false);  // Enable the next button to move to the next question

        currentQuestionIndex++;

        if (currentQuestionIndex >= totalQuestions) {
            // Calculate and display the final result after all questions
            calculateAndDisplayScore();
        }
    }

    // Called when the user clicks the "Next" button
    @FXML
    public void nextQuestion() {
        if (currentQuestionIndex < totalQuestions) {
            // Generate the next question
            currentQuestion = assessment.generateRandomQuestion(wordsList);
            question.setText(currentQuestion.toString());  // Update the question text

            // Clear the answer field for the next question
            answerField.clear();

            // Enable the submit button again for the new question
            submitButton.setDisable(false);
            nextButton.setDisable(true);  // Disable the next button until the user submits an answer
        }
    }

    // Calculate and display the score percentage
    private void calculateAndDisplayScore() {
        double percentage = ((double) score / totalQuestions) * 100;
        resultLabel.setText("Your score: " + score + "/" + totalQuestions + " (" + String.format("%.2f", percentage) + "%)");

        // Disable the submit button to prevent further answers
        submitButton.setDisable(true);
        nextButton.setDisable(true);  // Disable the next button as the quiz is over
    }
}
