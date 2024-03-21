package com.example.enghouse;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class GameController {
    @FXML
    private Button game_multiplechoice_button;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane contentAnchorPane;

    public GameController() {
    }

    @FXML
    private void handleMultipleChoiceButton() {
        loadFXML("MultipleChoice.fxml");
    }

    @FXML
    private void loadFXML(String fxmlFileName) {
        try {
            AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource(fxmlFileName));
            contentAnchorPane.getChildren().setAll(new Node[] { pane });
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }
}
