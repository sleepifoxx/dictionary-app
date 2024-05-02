package com.example.enghouse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;

public class Transition {
    @FXML
    private AnchorPane contentAnchorPane;

    @FXML
    public void handleTransition(String fxmlFileName) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/example/enghouse/views/" + fxmlFileName));
            FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(0.3), contentAnchorPane);
            TranslateTransition moveOutTransition = new TranslateTransition(Duration.seconds(0.3), pane);
            moveOutTransition.setToY(contentAnchorPane.getHeight());

            ParallelTransition parallelTransition = new ParallelTransition(fadeOutTransition, moveOutTransition);
            parallelTransition.setOnFinished(event -> {
                contentAnchorPane.getChildren().setAll(pane);

                pane.setTranslateY(contentAnchorPane.getHeight());

                FadeTransition fadeInTransition = new FadeTransition(Duration.seconds(0.3), pane);
                fadeInTransition.setFromValue(0);
                fadeInTransition.setToValue(1);

                TranslateTransition moveInTransition = new TranslateTransition(Duration.seconds(0.3), pane);
                moveInTransition.setToY(0);

                ParallelTransition newParallelTransition = new ParallelTransition(fadeInTransition, moveInTransition);
                newParallelTransition.play();
            });

            parallelTransition.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleLoadingTransition() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/example/enghouse/views/Menu.fxml"));
            FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(1), contentAnchorPane);
            TranslateTransition moveOutTransition = new TranslateTransition(Duration.seconds(1), pane);
            moveOutTransition.setToX(contentAnchorPane.getWidth());

            ParallelTransition parallelTransition = new ParallelTransition(fadeOutTransition, moveOutTransition);
            parallelTransition.setOnFinished(event -> {
                contentAnchorPane.getChildren().setAll(pane);

                pane.setTranslateX(contentAnchorPane.getWidth());

                FadeTransition fadeInTransition = new FadeTransition(Duration.seconds(1), pane);
                fadeInTransition.setFromValue(0);
                fadeInTransition.setToValue(1);

                TranslateTransition moveInTransition = new TranslateTransition(Duration.seconds(1), pane);
                moveInTransition.setToX(0);

                ParallelTransition newParallelTransition = new ParallelTransition(fadeInTransition, moveInTransition);
                newParallelTransition.play();
            });

            parallelTransition.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
