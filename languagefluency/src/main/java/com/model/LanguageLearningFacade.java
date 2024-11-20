package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class LanguageLearningFacade {

    private final UserList userList;
    private final CourseList courseList;
    private final LanguageList languageList;
    private User user;
    private ArrayList<Language> currentLanguage;
    private final WordsList wordsList;
    private Assessment assessments;
    private Questions question;

    /**
     * Initializes the facade, setting up user, course, and language lists,
     * and loads data such as words from storage.
     */
    public LanguageLearningFacade() {
        userList = UserList.getInstance();
        courseList = CourseList.getInstance();
        languageList = LanguageList.getInstance();
        wordsList = WordsList.getInstance();  // Use the singleton instance of WordsList
    }

    /**
     * Logs in a user based on provided credentials.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return true if login is successful, false otherwise
     */
    public boolean login(String username, String password) {
        User foundUser = userList.getUser(username);
        if (foundUser != null && foundUser.getPassword().equals(password)) {
            this.user = foundUser;
            return true;
        }
        return false;
    }

    /**
     * Logs out the currently logged-in user and clears the current language.
     */
    public void logout() {
        this.user = null;
        this.currentLanguage = null;
    }

    /**
     * Starts a specified course for the current user.
     *
     * @param course the course to start
     */
    public void startCourse(Course course) {
        if (user != null) {
            user.getCourses().add(course);
            course.setUserAccess(true);
        }
    }

    /**
     * Tracks the progress of a specific course for the current user.
     *
     * @param course the course to track progress for
     * @return the course progress percentage, or 0 if not accessible
     */
    public double trackCourseProgress(Course course) {
        if (user != null && course.getUserAccess()) {
            return course.getCourseProgress();
        }
        return 0.0;
    }

    /**
     * Retrieves all available languages in the system.
     *
     * @return a list of available languages
     */
    public List<Language> getAllLanguages() {
        return languageList.getLanguages();
    }

    /**
     * Selects a language for the current user based on language name.
     *
     * @param languageName the name of the language to select
     */
    public void selectLanguage(String languageName) {
        ArrayList<Language> language = languageList.getLanguages();
        if (language != null) {
            this.currentLanguage = language;
        }
    }

    /**
     * Retrieves all available courses in the system.
     *
     * @return a list of all available courses
     */
    public ArrayList<Course> getAllCourses() {
        return courseList.getCourses();
    }

    /**
     * Gets the list of words available in the system.
     *
     * @return the WordsList containing all words
     */
    public WordsList getWordsList() {
        return wordsList;
    }

    /**
     * Tracks the overall progress across all courses for the current user.
     *
     * @return the overall progress percentage
     */
    public double trackOverallProgress() {
        if (user != null) {
            double totalProgress = 0.0;
            for (Course course : user.getCourses()) {
                totalProgress += course.getCourseProgress();
            }
            return totalProgress / user.getCourses().size();
        }
        return 0.0;
    }

    /**
     * Retrieves all languages that match a specified keyword.
     *
     * @param keyWord the keyword to match languages against
     * @return a list of languages matching the keyword
     */
    public ArrayList<Language> getAllLanguagesByKeyWord(String keyWord) {
        ArrayList<Language> matchingLanguages = new ArrayList<>();
        for (Language language : languageList.getLanguages()) {
            if (language.getKeyWords().contains(keyWord)) {
                matchingLanguages.add(language);
            }
        }
        return matchingLanguages;
    }

    /**
     * Gets the currently logged-in user.
     *
     * @return the current user, or null if no user is logged in
     */
    public User getCurrentUser() {
        return user;
    }

    /**
     * Saves the current user's progress and logs them out.
     */
    public void saveAndLogout() {
        if (user != null) {
            userList.saveUsers();
            logout();
        }
    }

    /**
     * Registers a new user with the provided credentials.
     *
     * @param username the username of the new user
     * @param email    the email address of the new user
     * @param password the password for the new user
     */
    public void registerUser(String username, String email, String password) {
        UUID userId = UUID.randomUUID();
        User newUser = new User(userId, username, email, password, new ArrayList<>(), new HashMap<>(), new ArrayList<>(), null, new ArrayList<>(), null, "English");
        userList.addUser(newUser);
        userList.saveUsers();
    }

    /**
     * Checks if the current user has access to a specified course.
     *
     * @param course the course to check access for
     * @return true if the user has access, false otherwise
     */
    public boolean hasCourseAccess(Course course) {
        if (user != null) {
            return course.getUserAccess();
        }
        return false;
    }

     /**
     * Generates a random question from the available words.
     */
    public void getaQuestion() {
        // Generate a random question using the words from the loaded WordsList
         question = assessments.generateRandomQuestion(wordsList);

        // Check if the question is null before calling toString()
        if (question != null) {
            System.out.println("Generated Question: " + question.toString());
        } else {
            System.out.println("Error: Failed to generate a question.");
        }
    }

    public void answeraQuestion(Scanner k){
        System.out.println("Please enter your answer:");
        String userAnswer = k.nextLine().toLowerCase().trim();
        question.setUserAnswer(userAnswer);

        if(question.isCorrect()){
            System.out.println("Correct");
        }
        else{
            System.out.println("Incorrect");
        }

        assessments.addQuestion(question);
    }

    public void startAssessment1(Scanner k){

        for(int i =0; i < 5; i++){
            getaQuestion();
            answeraQuestion(k);
        }

        assessments.evaluatePerformance();
    }

}
