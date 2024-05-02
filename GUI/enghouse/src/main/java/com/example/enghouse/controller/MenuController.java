package com.example.enghouse.controller;

import javafx.fxml.FXML;

public class MenuController extends Transition {

    @FXML
    private void initialize() {
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
        handleTransition("Edit.fxml");
    }

    @FXML
    private void handleBookmarkButton() {
        handleTransition("Bookmark.fxml");
    }

    @FXML
    private void handleGameButton() {
        handleTransition("Game.fxml");
    }

    @FXML
    private void handleAboutButton() {
        handleTransition("About.fxml");
    }
}
