/**
 * Represents a flashcard question with a front side for the prompt and a back side for the answer
 * Includes user interactions like answering, checking correctness, and tracking completion status
 */
package com.model;

public class FlashcardQuestion {

    private final String frontInfo;
    private final String backAnswer;
    private String userAnswer;
    private boolean completed;
    private double flashcardProgress;

    /**
     * Initializes a flashcard with the provided prompt and answer.
     *
     * @param frontInfo the prompt on the front of the flashcard
     * @param backAnswer the answer on the back of the flashcard
     */
    public FlashcardQuestion(String frontInfo, String backAnswer) {
        this.frontInfo = frontInfo;
        this.backAnswer = backAnswer;
        this.userAnswer = "";
        this.completed = false;
        this.flashcardProgress = 0.0;
    }

    /**
     * Flips the card to show the answer.
     */
    public void flipCard() {
        System.out.println("Flipped! The answer is: " + backAnswer);
    }

    /**
     * Returns the answer on the back of the flashcard.
     *
     * @return the answer as a string
     */
    public String showDefinition() {
        return backAnswer;
    }

    /**
     * Submits the user's answer for the flashcard question.
     * @param userAnswer the answer provided by the user
     * @throws IllegalArgumentException if the answer is null or empty
     */
    public void submitAnswer(String userAnswer) {
        if (userAnswer == null || userAnswer.trim().isEmpty()) {
            throw new IllegalArgumentException("Please provide a valid answer.");
        }
        this.userAnswer = userAnswer;
    }

    /**
     * Marks the flashcard as completed if the user inputs "done".
     * @param userInput the user's input indicating completion
     */
    public void markAsCompleted(String userInput) {
        if ("done".equalsIgnoreCase(userInput.trim())) {
            this.flashcardProgress = 100.0;
            this.completed = true;
        }
    }

    /**
     * Checks if the user's answer is correct.
     * @return true if the answer is correct, false otherwise
     */
    public boolean checkAnswer() {
        return userAnswer.equalsIgnoreCase(backAnswer);
    }

    /**
     * Shows the correct answer for the flashcard
     *
     * @return the correct answer as a string
     */
    public String showCorrectAnswer() {
        return backAnswer;
    }

    public String getFrontInfo() {
        return frontInfo;
    }

    public String getBackAnswer() {
        return backAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    /**
     * Checks if the flashcard is completed
     * @return true if completed, false otherwise
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Gets the progress of the flashcard.
     * @return the progress as a percentage
     */
    public double getFlashcardProgress() {
        return flashcardProgress;
    }
}