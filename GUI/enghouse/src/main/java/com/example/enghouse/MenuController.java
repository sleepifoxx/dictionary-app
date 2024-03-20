package com.example.enghouse;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import javafx.scene.control.Button;

public class MenuController {
    @FXML
    private AnchorPane mainPane, contentAnchorPane;
    @FXML
    private Button homeButton;
    @FXML
    private AnchorPane contentPane;

    @FXML
    public void initialize() {
        loadFXML("Home.fxml");
    }

    @FXML
    private void handleHomeButton() {
        loadFXML("Home.fxml");
    }

    @FXML
    private void handleGoogleTranslateButton() {
        loadFXML("GoogleTranslate.fxml");
    }

    @FXML
    private void handleEditButton() {
        loadFXML("Edit.fxml");
    }

    @FXML
    private void handleBookmarkButton() {
        loadFXML("Bookmark.fxml");
    }

    @FXML
    private void handleGameButton() {
        loadFXML("Game.fxml");
    }

    @FXML
    private void handleAboutButton() {
        loadFXML("About.fxml");
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
}
