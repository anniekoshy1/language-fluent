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
    private ArrayList<FlashcardQuestion> flashcards;
    private Object currentLesson;
    private Object lesson;
        
            public Course(UUID id, String name, String description, boolean userAccess, double courseProgress, boolean completed,
                        ArrayList<Lesson> lessons, ArrayList<FlashcardQuestion> flashcards) {
                this.id = id;
                this.name = name;
                this.description = description;
                this.userAccess = userAccess;
                this.courseProgress = courseProgress;
                this.completed = completed;
                this.lessons = lessons;
                this.flashcards = flashcards;
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
        
            public void setCurrentLesson(Lesson lesson) {
                        this.lesson = currentLesson;
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

    public ArrayList<FlashcardQuestion> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(ArrayList<FlashcardQuestion> flashcards){
        this.flashcards = flashcards;
    }

    public void addFlashcard(FlashcardQuestion flashcard) {
        this.flashcards.add(flashcard);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Calculates and updates the course progress based on the completion status of lessons and flashcards.
     */
    public void calculateProgress() {
        int completedItems = 0;
        int totalItems = lessons.size() + flashcards.size();

        for (Lesson lesson : lessons) {
            if (lesson.isCompleted()) {
                completedItems++;
            }
        }
        for (FlashcardQuestion flashcard : flashcards) {
            if (flashcard.isCompleted()) {
                completedItems++;
            }
        }

        if(completedItems == 1){
            this.courseProgress = 50.0;
        }

        if(completedItems == 2){
            this.courseProgress = 100.0;
        }

        if (this.courseProgress == 100.0) {
            setCompleted(true);
        }
    }

    /**
     * Marks the course as completed and sets the progress to 100%.
     */
    public void setCompletedCourse() {
        this.completed = true;
        this.courseProgress = 100.0;
    }

    /**
     * Checks if the course is fully completed based on progress.
     *
     * @return true if the course progress is 100%, false otherwise
     */
    public boolean completedCourse() {
        return this.courseProgress == 100.0;
    }

    // In the Course class, add a method to get the current lesson
public Lesson getCurrentLesson() {
    for (Lesson lesson : lessons) {
        if (!lesson.isCompleted()) {  // Find the first incomplete lesson
            return lesson;
        }
    }
    return null;  // Return null if all lessons are completed
}

    // In the Course class, add a method to get the current lesson
    public FlashcardQuestion getCurrentFlashcard() {
        for (FlashcardQuestion flashcard : flashcards) {
            if (!flashcard.isCompleted()) {  // Find the first incomplete lesson
                return flashcard;
            }
        }
        return null;  // Return null if all lessons are completed
    }

}
