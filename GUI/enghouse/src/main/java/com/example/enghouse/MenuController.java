package com.example.enghouse;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class MenuController {
    @FXML
    private AnchorPane contentAnchorPane;
    @FXML
    private Button homeButton;

    @FXML
    public void initialize() {
        handleTransition("Home.fxml");
    }

    @FXML
    private void handleHomeButton() {
        handleTransition("Home.fxml");
    }

    @FXML
    private void handleGoogleTranslateButton() {
        handleTransition("GoogleTranslate.fxml");
    }

    @FXML
    private void handleEditButton() {
        // handleTransition();
        handleTransition("Edit.fxml");
    }

    @FXML
    private void handleBookmarkButton() {
        // handleTransition();
        handleTransition("Bookmark.fxml");
    }

    @FXML
    private void handleGameButton() {
        // handleTransition();
        handleTransition("Game.fxml");
    }

    @FXML
    private void handleAboutButton() {
        // handleTransition();
        handleTransition("About.fxml");
    }

    @FXML
    private void loadFXML(String fxmlFileName) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxmlFileName));
            contentAnchorPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleTransition(String fxmlFileName) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxmlFileName));
            pane.setTranslateY(contentAnchorPane.getHeight());
            contentAnchorPane.getChildren().setAll(pane);

            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.3), pane);
            transition.setToY(0);
            transition.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
