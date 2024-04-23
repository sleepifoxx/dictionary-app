package com.example.enghouse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.animation.KeyValue;

public class LoadingController extends Transition {
    @FXML
    private ProgressBar progressBar;

    @FXML
    private void initialize() {
        runProgressBar();
    }

    @FXML
    private void runProgressBar() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progressBar.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(3), new KeyValue(progressBar.progressProperty(), 1)));
        timeline.play();
        Timeline delay = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            handleLoadingTransition();
        }));
        delay.play();
    }
}
