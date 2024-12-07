package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

import com.languagefluent.App;
import com.model.Course;
import com.model.LanguageLearningFacade;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;



public class MainHomeController implements Initializable {

    @FXML private Label dynamicUser;
    @FXML private GridPane grid_books;
    @FXML private Button profileButton;

    private LanguageLearningFacade facade;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = LanguageLearningFacade.getInstance();
        user = facade.getCurrentUser();

        if (user != null) {
            dynamicUser.setText("Welcome, " + user.getUsername());
        } else {
            dynamicUser.setText("User not found.");
        }

        displayCourses();
    }

    private void displayCourses() {
        ArrayList<Course> courses = user != null ? user.getCourses() : new ArrayList<>();

        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);

            VBox vbox = new VBox();
            vbox.setSpacing(10);
            vbox.setStyle("-fx-alignment: center; -fx-padding: 10; -fx-border-color: lightgray;");
            vbox.setOnMouseClicked(event -> handleCourseClick(event, course)); // Add click handler

            Label courseName = new Label(course.getName());
            courseName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            Label courseDescription = new Label(course.getDescription());
            courseDescription.setWrapText(true);
            courseDescription.setStyle("-fx-font-size: 14px;");

            // Optional: Add an image placeholder for the course
            ImageView courseImage = new ImageView(new Image(getClass().getResourceAsStream("/images/starting.png")));
            courseImage.setFitWidth(100);
            courseImage.setPreserveRatio(true);

            vbox.getChildren().addAll(courseImage, courseName, courseDescription);

            // Add the VBox to the GridPane
            grid_books.add(vbox, i % 3, i / 3); // Adjust column and row
        }
    }

    @FXML
    private void handleCourseClick(MouseEvent event, Course course) {
        try {
            LanguageLearningFacade.getInstance().startCourse(course);
            App.setRoot("CourseHome");
            System.out.println("Clicked on course: " + course.getName());
            UUID selectedId = course.getId();

            user.setCurrentCourse(selectedId);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void onProfileButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("profile");
    }
    @FXML
    private void onLogoutButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("beginning");
    }


}