package com.example.enghouse;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class GameController {
    @FXML
    private Button game_multiplechoice_button, game_mergeword_button;
    @FXML
    private AnchorPane mainPane, contentAnchorPane;

    public GameController() {
    }

    @FXML
    private void handleMultipleChoiceButton() {
        loadFXML("MultipleChoice.fxml");
    }

    @FXML
    private void handleMergeWordButton() {
        loadFXML("MergeWord.fxml");
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
