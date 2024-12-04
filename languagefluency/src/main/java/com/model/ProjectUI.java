package com.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.narration.Narriator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProjectUI {

    private static ProjectUI instance;


    private LanguageLearningFacade facade;
    private Scanner scanner;
    private DataLoader dataLoader;
    private Lesson currentLesson;
    private Course course;
    private static Difficulty difficulty;
    private Assessment assessment;
    private Scene scene;


    public ProjectUI() {
        facade = new LanguageLearningFacade();
        scanner = new Scanner(System.in);
        dataLoader = new DataLoader();
    }

    public static ProjectUI getInstance() {
        if (instance == null) {
            instance = new ProjectUI();
        }
        return instance;
    }

    public void setRoot(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/languagefluent/" + fxml + ".fxml"));
    Parent root = fxmlLoader.load();
    Stage stage = (Stage) scene.getWindow();
    stage.setScene(new Scene(root));
}

//MAIN MENU
    public void start() {
        System.out.println("Welcome to the Language Learning System!");
        boolean exit = false;

        while (!exit) {
            displayMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    if (!isLoggedIn()) {
                        System.out.println("Please log in first.");
                    } else {
                        selectLanguage();
                    }
                    break;
                case 4:
                    if (!isLoggedIn()) {
                        System.out.println("Please log in first.");
                    } else {
                        startCourse();
                    }
                    break;
                case 5:
                    if (!isLoggedIn()) {
                        System.out.println("Please log in first.");
                    } else {
                        trackProgress();
                    }
                    break;
                case 6:
                    if (!isLoggedIn()) {
                        System.out.println("You are not logged in.");
                    } else {
                        logout();
                        System.out.println("Logging out.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Select Language");
        System.out.println("4. Start Course");
        System.out.println("5. Track Progress");
        System.out.println("6. Logout");
        System.out.print("Please enter your choice: ");
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
//LOGIN

private void login() {
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();

    if (facade.login(username, password)) {
        System.out.println("Login successful! Welcome, " + username);
    } else {
        System.out.println("Login failed. Please check your credentials.");
    }
}

    private boolean isLoggedIn() {
        return facade.getCurrentUser() != null;
    }
//REGISTER
    private void register() {
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        String email;
        String password;

        while (true) {
            System.out.print("Enter email: ");
            email = scanner.nextLine();
            if (email.contains("@") && email.contains(".")) {
                break;
            } else {
                System.out.println("Invalid email, please enter a valid email that includes '@' and '.'");
            }
        }

        while (true) {
            System.out.print("Enter new password: ");
            password = scanner.nextLine();
            if (password.length() >= DataConstants.MIN_PASSWORD_LENGTH && password.length() <= DataConstants.MAX_PASSWORD_LENGTH) {
                break;
            } else {
                System.out.println("Invalid password, please enter a password between " + DataConstants.MIN_PASSWORD_LENGTH + " and " + DataConstants.MAX_PASSWORD_LENGTH + " characters.");
            }
        }
        facade.registerUser(username, email, password);
        System.out.println("Registration successful! Please login to continue.");
    }
    //LANGUAGE
    private void selectLanguage() {
        if (!isLoggedIn()) {
            System.out.println("You must log in to select a language.");
            return;
        }

        System.out.println("Available languages:");
        for (Language language : facade.getAllLanguages()) {
            System.out.println("- " + language.getName());
        }
        System.out.print("Select a language: ");
        String languageName = scanner.nextLine();

        try {
            facade.selectLanguage(languageName);
            System.out.println("Language selected: " + languageName);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    //START COURSE
    private void startCourse() {
        ArrayList<Course> allCourses = facade.getAllCourses();
        if (!isLoggedIn()) {
            System.out.println("You must log in to start a course.");
            return;
        }
        if (allCourses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        System.out.println("Available courses: ");
        for (int i = 0; i < allCourses.size(); i++) {
            System.out.println((i + 1) + ". " + allCourses.get(i).getName());
        }
    
        System.out.print("Select a course by entering the corresponding number: ");
        int courseIndex;
        try {
            courseIndex = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }
    
        if (courseIndex < 0 || courseIndex >= allCourses.size()) {
            System.out.println("Invalid selection. Please choose a valid course number.");
            return;
        }
    
        Course selectedCourse = allCourses.get(courseIndex);
        facade.startCourse(selectedCourse); 
        this.course = selectedCourse;
        System.out.println("Course started: " + selectedCourse.getName());

        courseActivitiesMenu();
    }

    private void courseActivitiesMenu() {
        boolean exit = false;
        //ACTIVITIES MENU
        while (!exit) {
            System.out.println("Course Activities:");
            System.out.println("1. Flashcard Practice");
            System.out.println("2. Storytelling");
            System.out.println("3. Exit to Main Menu");
            System.out.print("Please enter your choice: ");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    startFlashcards();
                    break;
                case 2:
                    startLesson();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting course activities");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    //FLASHCARDS
    private void startFlashcards() {
        System.out.println("Starting Flashcard Practice...");
        
        List<FlashcardQuestion> flashcards = DataLoader.parseFlashcards("languagefluency/src/main/java/com/data/Words.json");

        if (flashcards.isEmpty()) {
            System.out.println("No flashcards available.");
            return;
        }

        for (FlashcardQuestion flashcard : flashcards) {
            
            if (flashcard.getFlashcardProgress() >= 100) {
                System.out.println("All flashcards completed! Returning to Course Activities.");
                startAssessment();  // Start assessment after all flashcards are completed
                return;
            }
            if (flashcard.isCompleted()) continue;

            System.out.println("Translate the following: " + flashcard.getFrontInfo());
            Narriator.playSound(flashcard.getFrontInfo());

            String userAnswer = scanner.nextLine().trim();
            flashcard.submitAnswer(userAnswer);

            if (flashcard.checkAnswer()) {
                System.out.println("Correct!");
                Narriator.playSound("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer is: " + flashcard.showCorrectAnswer());
                Narriator.playSound("The correct answer is: " + flashcard.showCorrectAnswer());
            }

            System.out.print("Enter 'done' to mark this flashcard as completed or press Enter to continue: ");
            String continueResponse = scanner.nextLine().trim();

            // Check if the user entered "done"1

            if (continueResponse.equalsIgnoreCase("done")) {
                flashcard.markAsCompleted(continueResponse);  // Mark flashcard as completed
                System.out.println("Flashcard marked as complete.");
                startAssessment();  // Start the assessment immediately
                return;  // Exit the flashcard loop
            } else {
                //hey chatgpt can you complete this
                //put in logic that will just do another flashcard when you press enter here
            }

            System.out.println("Flashcard Progress: " + flashcard.getFlashcardProgress() + "%");
        }

        System.out.println("Exiting Flashcard Practice.");
    }

    //LESSON/STORYTELLING
    private void startLesson() {
        System.out.println("Loading Storytelling...");

        Course currentCourse = facade.getCurrentUser().getCourses().get(0);
        if (currentCourse == null || currentCourse.getAllLessons().isEmpty()) {
            System.out.println("No lessons are available in the current course.");
            return;
        }

        if (!assessment.hasPassed()) {
            System.out.println("You need to pass the assessment to unlock Storytelling. Please retake the assessment.");
            return; // Exit the method if the assessment hasn't been passed
        }

        currentLesson = currentCourse.getAllLessons().get(0);
        currentCourse.setCurrentLesson(currentLesson);
        System.out.println("Starting Storytelling...");
        
        String spanishStory = currentLesson.getSpanishContent();
        String englishStory = currentLesson.getEnglishContent();

        System.out.println("Reading story in Spanish...");
        Narriator.playSound(spanishStory);
        System.out.println(spanishStory);

        System.out.println("Reading story in English...");
        Narriator.playSound(englishStory);
        System.out.println(englishStory);

        System.out.print("Enter 'done' to mark lesson as completed or press enteras completed or press enter: ");
        String continueResponse = scanner.nextLine().trim();

        if (continueResponse.equalsIgnoreCase("done")) {
            currentLesson.markAsCompleted();
            System.out.println("Storytelling marked completed.");
            course.calculateProgress();
            startAssessment2(); 
        } else {
            System.out.println("Exiting Storytelling.");
        }
    }

    private void trackProgress() {
        System.out.println("Tracking progress...");
        double progress = facade.trackOverallProgress();
        System.out.println("Your overall progress: " + progress + "%");
    }

    public void logout() {
        facade.saveAndLogout();
        System.out.println("You have been logged out.");
    }


    public void test(){
        facade.getaQuestion();
    }

    public static void main(String[] args) {
        ProjectUI languageInterface = new ProjectUI();
        languageInterface.start();
    }
}