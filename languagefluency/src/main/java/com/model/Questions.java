/**
 * Represents a question in a test, supporting different question types: True/False, multiple-choice, and open-ended questions.
 * Includes fields for the question text, answer options, and difficulty level.
 */
package com.model;

import java.util.List;


public class Questions {

    private String questionText;
    private boolean correctAnswer;
    private String userAnswer;
    private Difficulty difficulty;
    private List<String> options;
    private String correctOption;
    private String correctOpenEndedAnswer;

    /**
     * Constructor for True/False questions.
     * @param questionText  the text of the question
     * @param correctAnswer the correct answer 
     * @param difficulty    the difficulty level of the question
     */
    public Questions(String questionText, boolean correctAnswer, Difficulty difficulty) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
        this.options = null;
        this.correctOption = null;
        this.correctOpenEndedAnswer = null;
    }

    /**
     * Constructor for Multiple Choice questions.
     * @param questionText the text of the question
     * @param options      the list of answer options
     * @param correctOption the correct answer option
     * @param difficulty   the difficulty level of the question
     */
    public Questions(String questionText, List<String> options, String correctOption, Difficulty difficulty) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
        this.difficulty = difficulty;
        this.correctOpenEndedAnswer = null;
    }

    /**
     * Constructor for Open-Ended questions.
     * @param questionText           the text of the question
     * @param correctOpenEndedAnswer the correct answer for the question
     * @param difficulty             the difficulty level of the question
     */
    public Questions(String questionText, String correctOpenEndedAnswer, Difficulty difficulty) {
        this.questionText = questionText;
        this.correctOpenEndedAnswer = correctOpenEndedAnswer;
        this.difficulty = difficulty;
        this.options = null;
        this.correctOption = null;
    }

    /**
     * Gets the text of the question
     * @return the question text
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Sets the text of the question
     * @param questionText the new question text
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Gets the correct answer for True/False questions
     * @return the correct answer 
     */
    public boolean getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Sets the correct answer for True/False questions.
     * @param correctAnswer the correct answer
     */
    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Gets the user's submitted answer.
     * @return the user's answer
     */
    public String getUserAnswer() {
        return userAnswer;
    }

    /**
     * Submits the user's answer for the question
     * @param userAnswer the answer provided by the user
     */
    public void submitAnswer(String userAnswer) {
        this.userAnswer = userAnswer.trim();
    }

    /**
     * Checks if the user's answer matches the correct answer based on the question type.
     * @return true if the answer is correct, false otherwise
     */
    public boolean checkAnswers() {
        if (options != null && correctOption != null) {
            return userAnswer.equals(correctOption); // Multiple-choice question
        } else if (correctOpenEndedAnswer != null) {
            return userAnswer.equalsIgnoreCase(correctOpenEndedAnswer); // Open-ended question
        } else {
            return Boolean.parseBoolean(userAnswer) == correctAnswer; // True/False question
        }
    }

    /**
     * Gets the difficulty level of the question.
     * @return the difficulty level
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the difficulty level of the question.
     * @param difficulty the new difficulty level
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Gets the list of answer options for multiple-choice questions.
     * @return the list of options, or null if not applicable
     */
    public List<String> getOptions() {
        return options;
    }

    /**
     * Sets the list of answer options for multiple-choice questions.
     * @param options the list of options
     */
    public void setOptions(List<String> options) {
        this.options = options;
    }

    /**
     * Resets the user's answer for the question.
     */
    public void resetAnswer() {
        this.userAnswer = "";
    }

    /**
     * Returns a string representation of the question, including the question text, options, difficulty level, and the correct answer (if applicable).
     * @return a string describing the question
     */
    @Override
    public String toString() {
        return "Question: " + questionText +
            "\nOptions: " + options +
            "\nDifficulty: " + difficulty +
            "\nCorrect Answer: " + correctAnswer;
    }
}