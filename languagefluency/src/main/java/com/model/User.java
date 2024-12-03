package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class User {

    private final UUID id;
    private String username;
    private String email;
    private String password;
    private final ArrayList<Course> courses;
    private HashMap<UUID, Double> progress;
    private final ArrayList<UUID> completedCourses;
    private UUID currentCourseID;
    private final ArrayList<Language> languages;
    private UUID currentLanguageID;
    private String currentLanguageName;

    /**
     * Constructs a User with the specified attributes.
     *
     * @param id                unique identifier for the user
     * @param username          username of the user
     * @param email             email address of the user
     * @param password          password of the user
     * @param courses           list of courses the user is enrolled in
     * @param progress          map of course IDs to progress values for tracking completion
     * @param completedCourses  list of completed course IDs
     * @param currentCourseID   ID of the current course the user is working on
     * @param languages         list of languages the user is learning
     * @param currentLanguageID ID of the current language the user is learning
     * @param currentLanguageName name of the current language the user is learning
     */
    public User(UUID id, String username, String email, String password, ArrayList<Course> courses,
                HashMap<UUID, Double> progress, ArrayList<UUID> completedCourses, UUID currentCourseID,
                ArrayList<Language> languages, UUID currentLanguageID, String currentLanguageName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.courses = courses;
        this.progress = progress;
        this.completedCourses = completedCourses;
        this.currentCourseID = currentCourseID;
        this.languages = languages;
        this.currentLanguageID = currentLanguageID;
        this.currentLanguageName = currentLanguageName;
    }

    /**
     * Constructs a User with default course and language settings
     * @param id       unique identifier for the user
     * @param username username of the user
     * @param email    email address of the user
     * @param password password of the user
     */
    public User(UUID id, String username, String email, String password) {
        this(id, username, email, password, new ArrayList<>(), new HashMap<>(), new ArrayList<>(), null, new ArrayList<>(), null, "English");
    }

    /**
     * Gets the user's unique identifier
     * @return the UUID of the user
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the user's username
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's email address
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's password
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the list of courses the user is enrolled in
     * @return the list of courses
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * Adds a course to the user's list of enrolled courses
     * @param course the course to add
     */
    public void addCourse(Course course) {
        courses.add(course);
    }

    /**
     * Gets the user's progress in a specific course
     * @param courseId the ID of the course
     * @return the progress value (default 0.0 if not present)
     */
    public double getCourseProgress(UUID courseId) {
        return progress.getOrDefault(courseId, 0.0);
    }

    /**
     * Updates the user's progress for a specific course
     * @param courseId    the ID of the course
     * @param newProgress the new progress value to set
     */
    public void updateCourseProgress(UUID courseId, double newProgress) {
        progress.put(courseId, newProgress);
    }

    /**
     * Gets the list of completed courses for the user
     * @return the list of completed course IDs
     */
    public ArrayList<UUID> getCompletedCourses() {
        return completedCourses;
    }

    /**
     * Marks a course as completed for the user
     * @param courseId the ID of the course to mark as completed
     */
    public void completeCourse(UUID courseId) {
        if (!completedCourses.contains(courseId)) {
            completedCourses.add(courseId);
        }
    }

    /**
     * Gets the ID of the current course the user is working on
     * @return the current course ID
     */
    public UUID getCurrentCourse() {
        return currentCourseID;
    }

    /**
     * Sets the current course the user is working on
     * @param courseId the ID of the course to set as current
     */
    public void setCurrentCourse(UUID courseId) {
        this.currentCourseID = courseId;
    }

    /**
     * Gets the list of languages the user is learning
     * @return the list of languages
     */
    public ArrayList<Language> getLanguages() {
        return languages;
    }

    /**
     * Adds a new language to the user's list of languages
     * @param language the language to add
     */
    public void addLanguage(Language language) {
        languages.add(language);
    }

    /**
     * Gets the ID of the current language the user is learning
     * @return the current language ID
     */
    public UUID getCurrentLanguage() {
        return currentLanguageID;
    }

    /**
     * Sets the current language the user is learning
     * @param languageId the ID of the language to set as current
     */
    public void setCurrentLanguage(UUID languageId) {
        this.currentLanguageID = languageId;
    }

    /**
     * Gets the name of the current language the user is learning
     * @return the current language name
     */
    public String getCurrentLanguageName() {
        return currentLanguageName;
    }

    /**
     * Sets the name of the current language the user is learning.
     * @param languageName the name of the language to set as current
     */
    public void setCurrentLanguageName(String languageName) {
        this.currentLanguageName = languageName;
    }

    /**
     * Calculates the user's overall progress by checking completed courses
     * @return the percentage of completed courses
     */
    public double getCurrentUserProgress() {
        double totalCourses = courses.size();
        double completedCoursesCount = 0.0;

        for (Course course : courses) {
            // Check if the course is marked as completed
            if (completedCourses.contains(course.getId()) || course.isCompleted()) {
                completedCoursesCount++;
            }
        }

        // Calculate the progress as a percentage
        if (totalCourses > 0) {
            return (completedCoursesCount / totalCourses) * 100;
        } else {
            return 0.0;  // In case there are no courses
        }
    }

    /**
     * Gets the map tracking the user's progress across courses
     * @return the progress map
     */
    public HashMap<UUID, Double> getProgress() {
        return progress;
    }

    /**
     * Sets the progress map tracking the user's progress across courses
     * @param progress the new progress map
     */
    public void setProgress(HashMap<UUID, Double> progress) {
        this.progress = progress;
    }

    /**
     * Returns a string representation of the user's profile, including username, email, and current language
     * @return a string representing the user's profile
     */
    @Override
    public String toString() {
        return "User: " + username + "\nEmail: " + email + "\nCurrent Language: " + currentLanguageName;
    }
}
