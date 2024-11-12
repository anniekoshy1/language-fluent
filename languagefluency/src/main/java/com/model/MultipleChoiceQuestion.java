/**
 * Represents a multiple-choice question in a quiz or assessment.
 * Stores the question, possible answer choices, the correct answer, and the user's answer.
 */
package com.model;

import java.util.List;

public class MultipleChoiceQuestion {

    private final String question;
    private final List<String> choices;
    private final String correctAnswer;
    private String userAnswer;

    /**
     * Constructs a new MultipleChoiceQuestion with a specified question, list of choices, and correct answer.
     *
     * @param question      the question text
     * @param choices       the list of possible answer choices
     * @param correctAnswer the correct answer for the question
     */
    public MultipleChoiceQuestion(String question, List<String> choices, String correctAnswer) {
        this.question = question;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
        this.userAnswer = "";
    }

    /**
     * Gets the question text.
     *
     * @return the question text
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Gets the list of possible answer choices.
     *
     * @return the list of choices
     */
    public List<String> getChoices() {
        return choices;
    }

    /**
     * Submits the user's answer for the question.
     *
     * @param userAnswer the answer provided by the user
     */
    public void submitAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    /**
     * Checks if the user's answer matches the correct answer.
     *
     * @return true if the user's answer is correct, false otherwise
     */
    public boolean checkAnswer() {
        return userAnswer.equalsIgnoreCase(correctAnswer);
    }

    /**
     * Gets the correct answer for the question.
     *
     * @return the correct answer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Resets the user's answer to an empty state.
     */
    public void reset() {
        this.userAnswer = "";
    }

    /**
     * Gets the user's submitted answer.
     *
     * @return the user's answer
     */
    public String getUserAnswer() {
        return userAnswer;
    }
}