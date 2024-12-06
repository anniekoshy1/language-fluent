/**
 * Represents a flashcard question with a front side for the prompt and a back side for the answer
 * Includes user interactions like answering, checking correctness, and tracking completion status
 */
package com.model;

import java.util.UUID;

public class FlashcardQuestion {

    private String flashcardName;
    private final UUID flashcardId;
    private boolean completed;
    private double flashcardProgress;

    /**
     * Initializes a flashcard with the provided prompt and answer.
     *
     * @param frontInfo the prompt on the front of the flashcard
     * @param backAnswer the answer on the back of the flashcard
     */

    public FlashcardQuestion(String flashcardName, UUID flashcardId, boolean completed, double flashcardProgress) {
        this.flashcardName = flashcardName;
        this.flashcardId = flashcardId;
        this.completed = completed;
        this.flashcardProgress = flashcardProgress;
    }

    public String getFlashcardName() {
        return flashcardName;
    }

    public UUID getFlashcardId() {
        return flashcardId;
    }

    /**
     * Marks the lesson as completed, setting the progress to 100%
     */
    public void markAsCompleted() {
        this.completed = true;
        this.flashcardProgress = 100.0;
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

    public void setflashcardProgress(double flashcardProgress) {
        this.flashcardProgress = flashcardProgress;
        this.completed = flashcardProgress >= 100.0;
    }


}