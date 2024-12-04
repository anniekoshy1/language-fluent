/**
 * Represents a lesson within a course, including details like description, progress, English and Spanish content, and completion status
 */
package com.model;

import java.util.UUID;

public class Lesson {

    private final UUID id;
    private String description;
    private double lessonProgress;
    private String englishContent;
    private String spanishContent;
    private boolean completed;
    private String lessonName;

    /**
     * Constructs a Lesson with specified ID, description, progress, English content, and Spanish content.
     *
     * @param lessonName     the name of the lesson
     * @param id             the unique identifier for the lesson
     * @param description    a brief description of the lesson
     * @param lessonProgress the progress percentage of the lesson
     * @param englishContent the main English content of the lesson
     * @param spanishContent the main Spanish content of the lesson
     */
    public Lesson(String lessonName, UUID id, String description, double lessonProgress, String englishContent, String spanishContent) {
        this.lessonName = lessonName;
        this.id = id;
        this.description = description;
        this.lessonProgress = lessonProgress;
        this.englishContent = englishContent;
        this.spanishContent = spanishContent;
    }


    public String getLessonName() {
        return lessonName;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLessonProgress() {
        return lessonProgress;
    }

    /**
     * Sets the lesson progress and automatically marks it as completed if progress reaches 100%
     * @param lessonProgress the progress percentage of the lesson
     */
    public void setLessonProgress(double lessonProgress) {
        this.lessonProgress = lessonProgress;
        this.completed = lessonProgress >= 100.0;
    }

    public String getEnglishContent() {
        return englishContent;
    }

    public void setEnglishContent(String englishContent) {
        this.englishContent = englishContent;
    }

    public String getSpanishContent() {
        return spanishContent;
    }

    public void setSpanishContent(String spanishContent) {
        this.spanishContent = spanishContent;
    }

    /**
     * Checks if the lesson is completed
     * @return true if the lesson is completed, false otherwise
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Marks the lesson as completed, setting the progress to 100%
     */
    public void markAsCompleted() {
        this.completed = true;
        this.lessonProgress = 100.0;
    }
}
