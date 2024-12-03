/**
 * Represents a course in the language learning system, containing lessons, assessments, and progress tracking.
 */
package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Course {

    private String name;
    private String description;
    private boolean userAccess;
    private double courseProgress;
    private ArrayList<Lesson> lessons;
    private UUID id;
    private boolean completed;
    private FlashcardQuestion flashcard;
    private Lesson lesson;

    /**
     * Constructs a Course with all details.
     *
     * @param id the unique identifier of the course
     * @param name the name of the course
     * @param description the description of the course
     * @param userAccess the access status of the course
     * @param courseProgress the progress of the course
     * @param completed indicates whether the course is completed
     * @param lessons the list of lessons in the course
     * @param assessments the list of assessments in the course
     * @param completedAssessments the list of completed assessments
     * @param flashcard the flashcard question in the course
     */
    public Course(UUID id, String name, String description, boolean userAccess, double courseProgress, boolean completed,
        ArrayList<Lesson> lessons, ArrayList<Assessment> assessments, ArrayList<String> completedAssessments,
        FlashcardQuestion flashcard) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userAccess = userAccess;
        this.courseProgress = courseProgress;
        this.completed = completed;
        this.lessons = lessons;
        this.flashcard = flashcard;
    }

    /**
     * Constructs a Course with an ID and progress
     *
     * @param id the unique identifier of the course
     * @param courseProgress the progress of the course
     */
    public Course(UUID id, double courseProgress) {
        this.id = id;
        this.courseProgress = courseProgress;
    }

    public void setCurrentLesson(Lesson lesson){
        this.lesson = lesson;
    }

    public double getCourseProgress() {
        return this.courseProgress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Calculates and updates the course progress based on the completion status of lessons and flashcards
     */
    public void calculateProgress() {
        if (lesson.isCompleted() && flashcard.isCompleted()) {
            courseProgress = 100.0;
        } else if (lesson.isCompleted() || flashcard.isCompleted()) {
            courseProgress = 50.0;
        } else {
            courseProgress = 0.0;
        }
    
        if (courseProgress == 100.0) {
            setCompletedCourse();  // Automatically mark as completed
        }
    }
    

    public void setCourseProgress(double courseProgress) {
        if (courseProgress >= 0.0 && courseProgress <= 100.0) {
            this.courseProgress = courseProgress;
        }
    }

    public boolean getUserAccess() {
        return userAccess;
    }
    
    public void setUserAccess(boolean userAccess) {
        this.userAccess = userAccess;
    }

    public boolean isCompletedCourse() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
        if (completed) {
            this.courseProgress = 100.0;
        }
    }

    public ArrayList<Lesson> getAllLessons() {
        return lessons;
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }


    public UUID generateUUID() {
        return UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Marks the course as completed and sets the progress to 100%
     */
    public void setCompletedCourse() {
        this.completed = true;
        this.courseProgress = 100.0;
    }

    /**
     * Checks if the course is fully completed based on progress
     *
     * @return true if the course progress is 100%, false otherwise
     */
    public boolean completedCourse() {
        return this.courseProgress == 100.0;
    }
}