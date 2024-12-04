package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Represents a language within the language learning program.
 * Tracks progress, completed courses, and course access for a specific language.
 */
public class Language {
    private final UUID id;
    private String name;
    private double languageProgress;
    private ArrayList<UUID> completedCourses;
    private HashMap<UUID, Boolean> courseAccess;

    /**
     * Constructs a new Language instance with the specified details.
     * 
     * @param id              Unique identifier for the language.
     * @param name            Name of the language.
     * @param languageProgress Overall progress percentage in learning the language.
     * @param completedCourses List of completed course IDs for the language.
     * @param courseAccess     Map of course IDs to their access status.
     */
    public Language(UUID id, String name, double languageProgress, ArrayList<UUID> completedCourses, 
                    HashMap<UUID, Boolean> courseAccess) {
        this.id = id;
        this.name = name;
        this.languageProgress = languageProgress;
        this.completedCourses = completedCourses != null ? completedCourses : new ArrayList<>();
        this.courseAccess = courseAccess != null ? courseAccess : new HashMap<>();
    }

    /**
     * Gets the unique identifier for the language.
     * 
     * @return The unique identifier.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the name of the language.
     * 
     * @return The name of the language.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the overall progress percentage in learning the language.
     * 
     * @return The progress percentage.
     */
    public double getLanguageProgress() {
        return languageProgress;
    }

    /**
     * Sets the overall progress percentage in learning the language.
     * 
     * @param languageProgress The progress percentage to set.
     */
    public void setLanguageProgress(double languageProgress) {
        this.languageProgress = languageProgress;
    }

    /**
     * Gets the list of completed course IDs for the language.
     * 
     * @return The list of completed course IDs.
     */
    public ArrayList<UUID> getCompletedCourses() {
        return completedCourses;
    }

    /**
     * Sets the list of completed course IDs for the language.
     * 
     * @param completedCourses The list of completed course IDs to set.
     */
    public void setCompletedCourses(ArrayList<UUID> completedCourses) {
        this.completedCourses = completedCourses;
    }

    /**
     * Gets the map of course IDs to their access status.
     * 
     * @return The map of course access status.
     */
    public HashMap<UUID, Boolean> getCourseAccess() {
        return courseAccess;
    }

    /**
     * Sets the map of course IDs to their access status.
     * 
     * @param courseAccess The map of course access status to set.
     */
    public void setCourseAccess(HashMap<UUID, Boolean> courseAccess) {
        this.courseAccess = courseAccess;
    }
}
