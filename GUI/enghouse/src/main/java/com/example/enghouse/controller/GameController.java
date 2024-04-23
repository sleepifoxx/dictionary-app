package com.example.enghouse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameController extends Transition {
    @FXML
    private Button game_multiplechoice_button, game_mergeword_button;

    @FXML
    private void handleMultipleChoiceButton() {
        handleTransition("MultipleChoice.fxml");
    }

    @FXML
    private void handleMergeWordButton() {
        handleTransition("MergeWord.fxml");
    }
}
