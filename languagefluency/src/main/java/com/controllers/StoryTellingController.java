package com.controllers;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.languagefluent.App;
import com.narration.Narriator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


public class StoryTellingController implements Initializable {

    @FXML 
    private Button playButton;

    @FXML
    private Button backToCourseHomeButton;

    @FXML 
    private Label storyLabel;

    private String spanishContent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadStoryFromJSON();
    }

    private void loadStoryFromJSON() {
        try {
            JSONParser jsonParser = new JSONParser();
            FileReader fReader = new FileReader("languagefluency/src/main/java/com/data/Courses.json");
            JSONArray coursesArray = (JSONArray) jsonParser.parse(fReader);
    
            if (coursesArray.isEmpty()) {
                System.out.println("No courses found in the JSON file.");
                return;
            }
    
            // Debugging: Print the entire coursesArray to see its structure
            System.out.println("Courses Array: " + coursesArray.toJSONString());
    
            JSONObject firstCourse = (JSONObject) coursesArray.get(0);
    
            if (!firstCourse.containsKey("lessons")) {
                System.out.println("Lessons not found in the first course.");
                return;
            }
    
            JSONArray lessonsArray = (JSONArray) firstCourse.get("lessons");
    
            if (lessonsArray.isEmpty()) {
                System.out.println("No lessons found in the first course.");
                return;
            }
    
            // Debugging: Print the lessons array
            System.out.println("Lessons Array: " + lessonsArray.toJSONString());
    
            JSONObject firstLesson = (JSONObject) lessonsArray.get(0);
    
            // Check if spanishContent exists in the lesson
            if (!firstLesson.containsKey("spanishContent")) {
                System.out.println("Spanish content not found in this lesson.");
                return;
            }
    
            spanishContent = (String) firstLesson.get("spanishContent");
    
            if (spanishContent == null || spanishContent.isEmpty()) {
                System.out.println("Spanish content is empty.");
            } else {
                System.out.println("Spanish Content: " + spanishContent);
            }
    
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading the JSON file.");
        }
    }

    @FXML
    public void onPlayButtonClicked() {
        if (spanishContent != null && !spanishContent.isEmpty()) {
            storyLabel.setText(spanishContent);
            readStoryAloud(spanishContent);
        }
    }

    private void readStoryAloud(String content) {
        Narriator.playSound(content);
    }

    @FXML
public void onBackToCourseHomeClicked(MouseEvent event) {
    try {
        // Navigate back to CourseHome
        App.setRoot("CourseHome");
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Error switching to CourseHome.fxml");
    }
}


}