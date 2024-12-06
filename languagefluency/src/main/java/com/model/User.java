package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class User {

    // Fields
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

    // Constructors
    /**
     * Constructs a User with the specified attributes.
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
     * Constructs a User with default course and language settings.
     */
    public User(UUID id, String username, String email, String password) {
        this(id, username, email, password, new ArrayList<>(), new HashMap<>(), 
            new ArrayList<>(), null, new ArrayList<>(), null, null);
    }
    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public HashMap<UUID, Double> getProgress() {
        return progress;
    }

    public void setProgress(HashMap<UUID, Double> progress) {
        this.progress = progress;
    }

    public ArrayList<UUID> getCompletedCourses() {
        return completedCourses;
    }

    public UUID getCurrentCourse() {
        return currentCourseID;
    }

    public void setCurrentCourse(UUID courseId) {
        this.currentCourseID = courseId;
    }

    public Course getCurrentCourseDetails(){
        if (currentCourseID != null){
            return CourseList.getInstance().getCourse(currentCourseID.toString());
        }
        return null;
    }

    public ArrayList<Language> getLanguages() {
        return languages;
    }

    public UUID getCurrentLanguage() {
        return currentLanguageID;
    }

    public void setCurrentLanguage(UUID languageId) {
        this.currentLanguageID = languageId;
    }

    public String getCurrentLanguageName() {
        return currentLanguageName;
    }

    public void setCurrentLanguageName(String languageName) {
        this.currentLanguageName = languageName;
    }

    // Course-related Methods
    public void addCourse(Course course) {
        courses.add(course);
    }

    public double getCourseProgress(UUID courseId) {
        return progress.getOrDefault(courseId, 0.0);
    }

    public void completeCourse(UUID courseId) {
        if (!completedCourses.contains(courseId)) {
            completedCourses.add(courseId);
        }
    }

    public void updateCourseProgress(UUID courseId, double progress) {
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                course.setCourseProgress(progress);
                if (progress == 100.0) {
                    CourseList.getInstance().completeCourse(courseId);
                }
                break;
            }
        }
        this.progress.put(courseId, progress);
    }

    public double getOverallProgress() {
        int completed = 0;
        for (Course course : courses) {
            if (course.completedCourse()) {
                completed++;
            }
        }
        return (double) completed / courses.size() * 100.0;
    }

    // Language-related Methods
    public void addLanguage(Language language) {
        languages.add(language);
    }

    // Utility Methods
    @Override
    public String toString() {
        return "User: " + username + "\nEmail: " + email + "\nCurrent Language: " + currentLanguageName;
    }
}
