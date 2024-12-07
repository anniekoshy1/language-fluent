package com.controllers;

import java.util.List;

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
    private Label question;

    @FXML
    private VBox optionsBox;

    @FXML
    private TextField answerField;

    @FXML
    private Button submitButton;

    @FXML
    private Button nextButton;

    @FXML
    private Label scoreLabel;

    private Assessment assessment;
    private WordsList wordsList;
    private List<Questions> questionList;
    private int currentQuestionIndex;
    private int score;

    @FXML
    public void initialize() {
        // Initialize the assessment and word list
        wordsList = WordsList.getInstance();
        assessment = new Assessment();

        question.setText(assessment.generateRandomQuestion(wordsList).toString());

        // Start with the first question
        currentQuestionIndex = 0;
        score ++;

        
    }

  
}
