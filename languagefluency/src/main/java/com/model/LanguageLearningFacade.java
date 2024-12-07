package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class LanguageLearningFacade {

    private final UserList userList;
    private final CourseList courseList;
    private final LanguageList languageList;
    private User user;
    private Course course;
    private Lesson lesson;
    private ArrayList<Language> currentLanguage;
    private final WordsList wordsList;
    private Assessment assessments;
    private Questions question;
    private static LanguageLearningFacade facade;

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

    public static LanguageLearningFacade getInstance(){
        if(facade == null){
            facade = new LanguageLearningFacade();
        }
        return facade;
    }    
    /**
     * Registers a new user with the provided credentials.
     *
     * @param username the username of the new user
     * @param email    the email address of the new user
     * @param password the password for the new user
     */
    public boolean registerUser(String username, String email, String password) {
    if (username == null || username.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
        return false; // Invalid input
    }

    UUID userId = UUID.randomUUID();
    User newUser = new User(userId, username, email, password);
    userList.addUser(newUser);
    userList.saveUsers();
    return true; // Registration successful
}


    /**
     * Logs in a user based on provided credentials.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return true if login is successful, false otherwise
     */
    public boolean login(String username, String password) {
        User user = userList.getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            this.user = user;
            return true;
        }
        return false;
    }

    public User getCurrentUser(){
        return user;
    }

        /**
     * Selects a language for the current user based on language name.
     *
     * @param languageName the name of the language to select
     */
    public void selectLanguage(String languageName) {
        if (user != null) {
            for (Language language : languageList.getLanguages()) {
                if (language.getName().equalsIgnoreCase(languageName)) {
                    user.setCurrentLanguage(language.getId());
                    user.setCurrentLanguageName(language.getName());
                    UserList.getInstance().saveUsers(); // Save changes to JSON
                    System.out.println("Language updated: " + language.getName());
                    return;
                }
            }
            System.out.println("Language not found: " + languageName);
        } else {
            System.out.println("No user logged in to select a language.");
        }
    }

    public void displayCourses() {
        if (user == null) {
            System.out.println("No user is logged in.");
            return;
        }
        ArrayList<Course> courses = CourseList.getInstance().getAvailableCourses(user.getCurrentCourse());
        System.out.println("Courses:");
        for (Course course : courses) {
            if (course.getId().equals(user.getCurrentCourse())) {
                System.out.println(course.getName() + " - [Current Course]");
            } else if (!course.getUserAccess()) {
                System.out.println(course.getName() + " - [Locked]");
            } else {
                System.out.println(course.getName());
            }
        }

    }



    /**
     * Logs out the currently logaged-in user and clears the current language.
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
            user.setCurrentCourse(course.getId());
            UserList.getInstance().saveUsers();


        this.course = CourseList.getInstance().getCourse(course.getId().toString());
        if (this.course != null) {
            System.out.println("Course updated to: " + this.course.getName());
            System.out.println("Lessons for the new course:");
            ArrayList<Lesson> lessons = this.course.getAllLessons();
            if (lessons != null && !lessons.isEmpty()) {
                for (Lesson lesson : lessons) {
                    System.out.println("- " + lesson.getLessonName());
                }
            } else {
                System.out.println("No lessons found for this course.");
            }
        } else {
            System.out.println("Failed to find the new course in the system.");
        }
    }
    
    }

    public Course getCurrentCourseDetails() {
    if (user != null && user.getCurrentCourse() != null) {
        return CourseList.getInstance().getCourse(user.getCurrentCourse().toString());
    }
    return null;
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
     * Retrieves all available courses in the system.
     *
     * @return a list of all available courses
     */
    public ArrayList<Course> getAllCourses() {
        return user.getCourses();
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
     * Saves the current user's progress and logs them out.
     */
    public void saveAndLogout() {
        if (user != null) {
            userList.saveUsers();
            logout();
        }
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

    public void startLessonAssessment(Scanner scanner) {
        
        System.out.println("nejf");
            // Reset assessment state before starting
            assessments = new Assessment();
            
            for (int i = 0; i < 5; i++) {
                getaQuestion();  // Generate and display random question
                answeraQuestion(scanner);  // Allow the user to answer the question
            }
    
            // After answering all questions, evaluate the performance
            assessments.evaluatePerformance();
            
    
            System.out.println("You do not have access to this course.");

    }

    public static void main(String[] args) {
        
    Scanner scanner = new Scanner(System.in);

    // Get the singleton instance of LanguageLearningFacade
    LanguageLearningFacade facade = LanguageLearningFacade.getInstance();

    // Call the startLessonAssessment method
    System.out.println("Starting Lesson Assessment...");
    facade.getaQuestion();

    // Close the Scanner after use
    scanner.close();
}
}




