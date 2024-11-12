/**
 * Represents a starter test consisting of a list of questions. 
 * Manages the user's score, total questions, and difficulty level determination based on performance
 */
package com.model;

import java.util.List;

public class StarterTest {

    private List<Questions> questions;
    private int userScore;
    private int totalQuestions;

    /**
     * Constructs a StarterTest with a specified list of questions.
     * @param questions the list of questions for the starter test
     */
    public StarterTest(List<Questions> questions) {
        this.questions = questions;
        this.totalQuestions = questions.size();
        this.userScore = 0;
    }

    /**
     * Gets the list of questions in the test.
     * @return the list of questions
     */
    public List<Questions> getQuestions() {
        return questions;
    }

    /**
     * Sets the list of questions for the test and updates the total question count.
     * @param questions the new list of questions
     */
    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
        this.totalQuestions = questions.size();
    }

    /**
     * Submits the user's answers for the test and calculates the score.
     */
    public void submitTest() {
        int correctAnswers = 0;
        for (Questions question : questions) {
            if (question.checkAnswers()) {
                correctAnswers++;
            }
        }
        userScore = correctAnswers;
    }

    /**
     * Gets the user's score on the test.
     * @return the user's score
     */
    public int getUserScore() {
        return userScore;
    }

    /**
     * Gets the user's score as a percentage of total questions.
     * @return the score percentage, or 0.0 if there are no questions
     */
    public double getScorePercentage() {
        if (totalQuestions == 0) {
            return 0.0;
        }
        return (double) userScore / totalQuestions * 100.0;
    }

    /**
     * Resets the test by clearing the user's score and resetting all answers.
     */
    public void resetTest() {
        userScore = 0;
        for (Questions question : questions) {
            question.resetAnswer();
        }
    }

    /**
     * Determines the difficulty level based on the user's score percentage.
     * @return the determined difficulty level (ADVANCED, INTERMEDIATE, or RUDIMENTARY)
     */
    public Difficulty determineLevel() {
        double percentage = getScorePercentage();
        if (percentage >= 90) {
            return Difficulty.ADVANCED;
        } else if (percentage >= 60) {
            return Difficulty.INTERMEDIATE;
        } else {
            return Difficulty.RUDIMENTARY;
        }
    }

    /**
     * Gets the total number of questions in the test.
     * @return the total number of questions
     */
    public int getTotalQuestions() {
        return totalQuestions;
    }

    /**
     * Adds a question to the test and updates the total question count.
     * @param question the question to add
     */
    public void addQuestion(Questions question) {
        questions.add(question);
        totalQuestions++;
    }

    /**
     * Removes a question from the test and updates the total question count.
     * @param question the question to remove
     */
    public void removeQuestion(Questions question) {
        questions.remove(question);
        totalQuestions--;
    }
}