package com.controllers;

import java.io.IOException;

import com.languagefluent.App;
import com.model.Assessment;
import com.model.Course;
import com.model.CourseList;
import com.model.FlashcardQuestion;
import com.model.LanguageLearningFacade;
import com.model.Lesson;
import com.model.Questions;
import com.model.User;
import com.model.WordsList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class Assessment2Controller {

    private LanguageLearningFacade facade;
    private User user;
    private Course currentCourse;
    private Lesson currentLesson;
    private FlashcardQuestion currentFlashcard;
    private CourseList courseList;
    @FXML
    private Label question;  // Label to display the question text

    @FXML
    private TextField answerField;  // Field where user types their answer

    @FXML
    private Button submitButton;  // Submit button to check the answer

    @FXML
    private Button nextButton;  // Button to move to the next question

    @FXML
    private Button exitButton;  // Button to exit the assessment

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
        wordsList = WordsList.getInstance();
        facade = LanguageLearningFacade.getInstance();
        user = facade.getCurrentUser();
        currentCourse = facade.getCurrentCourseDetails();
        courseList = CourseList.getInstance();
        assessment = new Assessment();

        // Generate the first question
        currentQuestion = assessment.generateRandomQuestion(wordsList);
        question.setText(currentQuestion.toString());

        // Initialize score
        score = 0;
        resultLabel.setText("");  // Clear result label initially
    }

    @FXML
    public void submitAnswer() {
        String userAnswer = answerField.getText().trim().toLowerCase();
        currentQuestion.setUserAnswer(userAnswer);

        if (currentQuestion.isCorrect()) {
            score++;
        }

        scoreLabel.setText("Score: " + score);

        submitButton.setDisable(true);
        nextButton.setDisable(false);

        currentQuestionIndex++;

        if (currentQuestionIndex >= totalQuestions) {
            calculateAndDisplayScore();
        }
    }

    @FXML
    public void nextQuestion() {
        if (currentQuestionIndex < totalQuestions) {
            currentQuestion = assessment.generateRandomQuestion(wordsList);
            question.setText(currentQuestion.toString());

            answerField.clear();
            submitButton.setDisable(false);
            nextButton.setDisable(true);
        }
    }

    private void calculateAndDisplayScore() {
        double percentage = ((double) score / totalQuestions) * 100;
        resultLabel.setText("Your score: " + score + "/" + totalQuestions + " (" + String.format("%.2f", percentage) + "%)");

        submitButton.setDisable(true);
        nextButton.setDisable(true);
    }

@FXML
private void onExitButtonClicked(MouseEvent event) throws IOException {
    
    currentFlashcard = currentCourse.getCurrentFlashcard();
    currentFlashcard.markAsCompleted();
    currentCourse.calculateProgress();
    courseList.saveCourses();


    App.setRoot("CourseHome"); // Navigates to the course home view
}

}