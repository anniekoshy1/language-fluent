/**
 * Represents an assessment in the language learning system, consisting of multiple questions and associated user scores and attempts.
 */
package com.model;

import java.util.List;
import java.util.UUID;

public class Assessment {

    /**
     * Enum representing the types of assessments available.
     */
    public enum AssessmentType {
        MULTIPLE_CHOICE, TRUE_FALSE, OPEN_ENDED, MATCHING
    }

    private AssessmentType type;  
    private int userScore;  
    private List<Questions> questions;  
    private int attempts;  
    private UUID id;
    private boolean passedAssessment;

    /**
     * Constructs an Assessment with the specified ID, type, and list of questions.
     * Initializes the user score and attempt count to zero.
     *
     * @param id the unique identifier for the assessment
     * @param type the type of assessment
     * @param questions the list of questions in the assessment
     */
    public Assessment(UUID id, AssessmentType type, List<Questions> questions) {
        this.id = id;
        this.type = type;
        this.userScore = 0;
        this.attempts = 0;
        this.questions = questions;
        this.passedAssessment = false;
    }

    /**
     * Returns the user's score on the assessment.
     *
     * @return the user score as an integer
     */
    public int getResults() {
        return userScore;
    }

    /**
     * Calculates the user's score based on the number of correct answers in the assessment.
     * Sets passedAssessment to true if the score is 70% or higher.
     *
     * @return the calculated score as a percentage
     */
    public int calculateScore() {
        int correctAnswers = 0;
        for (Questions question : questions) {
            if (question.checkAnswers()) {
                correctAnswers++;
            }
        }
        this.userScore = (int) ((double) correctAnswers / questions.size() * 100);
        this.passedAssessment = userScore >= 70; // Set passedAssessment based on score
        return userScore;
    }

    /**
     * Returns whether the user has passed the assessment.
     *
     * @return true if the user passed, false otherwise
     */
    public boolean hasPassed() {
        return passedAssessment;
    }

    /**
     * Calculates a rating based on the user's score, returning a rating between 1 and 5 stars.
     *
     * @return the rating as an integer
     */
    public int calculateRating() {
        if (userScore == 100) {
            return 5;
        } else if (userScore == 80) {
            return 4;
        } else if (userScore == 60) {
            return 3;
        } else if (userScore == 40) {
            return 2;
        } else {
            return 1;
        }
    }

    /**
     * Resets the user score and increments the attempt count, allowing for a retake of the assessment.
     */
    public void retakeAssessment() {
        attempts++; 
        this.userScore = 0;
        this.passedAssessment = false;
    }

    /**
     * Generates and returns a random UUID.
     *
     * @return a new random UUID
     */
    public UUID generateUUID() {
        return UUID.randomUUID();
    }

    /**
     * Returns the unique identifier for this assessment.
     *
     * @return the assessment UUID
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier for this assessment.
     *
     * @param id the UUID to set
     */
    public void setUUID(UUID id) {
        this.id = id;
    }

    /**
     * Returns the list of questions in this assessment.
     *
     * @return the list of questions
     */
    public List<Questions> getQuestions() {
        return questions;
    }

    /**
     * Sets the list of questions for this assessment.
     *
     * @param questions the list of questions to set
     */
    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

    /**
     * Returns the type of this assessment.
     *
     * @return the assessment type
     */
    public AssessmentType getType() {
        return type;
    }

    /**
     * Sets the type of this assessment.
     *
     * @param type the assessment type to set
     */
    public void setType(AssessmentType type) {
        this.type = type;
    }

    /**
     * Returns the number of attempts made on this assessment.
     *
     * @return the attempt count
     */
    public int getAttempts() {
        return attempts;
    }

    /**
     * Sets the number of attempts for this assessment.
     *
     * @param attempts the number of attempts to set
     */
    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    /**
     * Returns a string representation of the assessment, including its ID and user score.
     *
     * @return a string representation of the assessment
     */
    @Override
    public String toString() {
        return "Assessment ID: " + id + ", Score: " + userScore;
    }
}
