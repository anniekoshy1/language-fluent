/**
 * Represents a language within the language learning system, tracking a user's progress, completed courses, assessments, and keywords associated with the language
 */
package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Language {

    private final UUID id;
    private User user;
    private StarterTest starterTest;
    private final String name;
    private double coursePercentage;
    private double totalPercentage;
    private double languageProgress;
    private ArrayList<String> keyWords;
    private ArrayList<Course> completedCourses;
    private ArrayList<Assessment> completedAssessments;
    private HashMap<Course, Boolean> courseAccess;

    /**
     * Constructs a Language instance associated with a user and language name
     * @param user the user learning the language
     * @param name the name of the language
     */
    public Language(User user, String name) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.name = name;
        this.coursePercentage = 0.0;
        this.totalPercentage = 0.0;
        this.languageProgress = 0.0;
        this.keyWords = new ArrayList<>();
        this.completedCourses = new ArrayList<>();
        this.completedAssessments = new ArrayList<>();
        this.courseAccess = new HashMap<>();
    }

    /**
     * Constructs a Language instance with the specified name.
     * @param name the name of the language
     */
    public Language(String name) {
        this(UUID.randomUUID(), name);
    }

    /**
     * Constructs a Language instance with a specified UUID and name.
     * @param id   the unique identifier of the language
     * @param name the name of the language
     */
    public Language(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Sets access to a specific course in the language.
     * @param course the course to set access for
     * @param access the access status (true if accessible)
     */
    public void setCourseAccess(Course course, boolean access) {
        courseAccess.put(course, access);
    }

    public double getTotalPercentage() {
        return totalPercentage;
    }

    public double getCoursePercentage() {
        return coursePercentage;
    }

    public double getLanguageProgress() {
        return languageProgress;
    }

    /**
     * Sets the progress percentage of the language and updates the total percentage.
     * @param languageProgress the progress percentage in learning the language
     */
    public void setLanguageProgress(double languageProgress) {
        this.languageProgress = languageProgress;
        updateTotalPercentage();
    }

    public ArrayList<Course> getCompletedCourses() {
        return completedCourses;
    }

    /**
     * Sets the list of completed courses and updates the course completion percentage.
     * @param completedCourses the list of completed courses
     */
    public void setCompletedCourses(ArrayList<Course> completedCourses) {
        this.completedCourses = completedCourses;
        updateCoursePercentage();
    }

    /**
     * Checks if the user has taken the starter test.
     * @return true if the starter test is taken, false otherwise
     */
    public boolean takenStarterTest() {
        return starterTest != null;
    }

    /**
     * Adds a keyword associated with the language.
     * @param keyWord the keyword to add
     */
    public void addKeyWord(String keyWord) {
        keyWords.add(keyWord);
    }

    /**
     * Updates the overall total percentage by averaging course and language progress.
     */
    private void updateTotalPercentage() {
        this.totalPercentage = (this.coursePercentage + this.languageProgress) / 2.0;
    }

    /**
     * Updates the course completion percentage to 100 if there are completed courses.
     */
    private void updateCoursePercentage() {
        if (!completedCourses.isEmpty()) {
            this.coursePercentage = 100.0;
        }
    }

    public UUID getId() {
        return id;
    }

    public ArrayList<String> getKeyWords() {
        return keyWords;
    }

    /**
     * Adds a completed assessment to the list of completed assessments
     * @param assessment the assessment to add
     */
    public void addCompletedAssessment(Assessment assessment) {
        completedAssessments.add(assessment);
    }

    public ArrayList<Assessment> getCompletedAssessments() {
        return completedAssessments;
    }
}