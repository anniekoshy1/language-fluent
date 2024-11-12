/**
 * Represents a true/false question
 * This class handles the correct answer, user's answer submission, and answer checking
 */
package com.model;

public class TrueFalseQuestion extends Questions {

    private final boolean correctAnswer;
    private boolean userAnswer;

    /**
     * Constructs a TrueFalseQuestion with the specified question text, correct answer, and difficulty level
     * @param questionText  the text of the question
     * @param correctAnswer the correct answer
     * @param difficulty    the difficulty level of the question
     */
    public TrueFalseQuestion(String questionText, boolean correctAnswer, Difficulty difficulty) {
        super(questionText, correctAnswer, difficulty);
        this.correctAnswer = correctAnswer;
        this.userAnswer = false;
    }

    /**
     * Submits the user's answer for the true/false question
     * @param userAnswer the user's answer
     */
    public void submitAnswer(boolean userAnswer) {
        this.userAnswer = userAnswer;
        super.submitAnswer(userAnswer ? "True" : "False");
    }

    /**
     * Checks if the user's submitted answer matches the correct answer
     * @return true if the user's answer is correct; false otherwise
     */
    @Override
    public boolean checkAnswers() {
        return this.userAnswer == this.correctAnswer;
    }

    /**
     * Gets the correct answer for the true/false question
     * @return the correct answer 
     */
    @Override
    public boolean getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Gets the user's submitted answer as a string
     * @return the user's answer as "True" or "False"
     */
    @Override
    public String getUserAnswer() {
        return userAnswer ? "True" : "False";
    }

    /**
     * Resets the user's answer to the default value and resets the superclass answer.
     */
    @Override
    public void resetAnswer() {
        this.userAnswer = false;
        super.resetAnswer();
    }

    /**
     * Returns a string representation of the true/false question, including the question text and correct answer
     * @return a string describing the true/false question
     */
    @Override
    public String toString() {
        return "True/False Question: " + getQuestionText() + "\nCorrect Answer: " + (correctAnswer ? "True" : "False");
    }
}