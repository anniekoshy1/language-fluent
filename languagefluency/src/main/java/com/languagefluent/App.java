package com.languagefluent;

import java.io.IOException;

import com.model.FlashcardChild;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private static FlashcardChild selectedFlashcard;

    public static void setSelectedFlashcard(FlashcardChild flashcard) {
        selectedFlashcard = flashcard;
    }

    public static FlashcardChild getSelectedFlashcard() {
        return selectedFlashcard;
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("beginning"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}