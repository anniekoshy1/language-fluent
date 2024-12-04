package com.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class StoryTellingController {

    @FXML
    private MediaView mediaView;

    @FXML
    private Button playButton;

    private MediaPlayer mediaPlayer;

    @FXML
    public void initialize() {
        // Load the audio file from resources
        String audioPath = getClass().getResource("/audio/story.mp3").toExternalForm();
        Media media = new Media(audioPath);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        // Update button text based on media player status
        mediaPlayer.setOnReady(() -> playButton.setText("Play Audio"));
        mediaPlayer.setOnEndOfMedia(() -> playButton.setText("Replay Audio"));
    }

    @FXML
    public void onPlayButtonClicked() {
        MediaPlayer.Status status = mediaPlayer.getStatus();
        if (status == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            playButton.setText("Play Audio");
        } else {
            mediaPlayer.play();
            playButton.setText("Pause Audio");
        }
    }

    @FXML
    public void onCloseButtonClicked() {
        // Close the current window
        Stage stage = (Stage) playButton.getScene().getWindow();
        stage.close();
    }
}
