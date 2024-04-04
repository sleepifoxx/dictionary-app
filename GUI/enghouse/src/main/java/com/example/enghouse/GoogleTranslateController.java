package com.example.enghouse;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.io.IOException;
import javafx.scene.text.Text;

public class GoogleTranslateController {
    private String language_target, language_explain;

    @FXML
    private TextArea Google_Translate_explain;

    @FXML
    private TextArea Google_Translate_target;

    @FXML
    private Button swap;

    @FXML
    private Text text_target, text_explain;

    @FXML
    private void initialize() {
        language_target = "en";
        language_explain = "vi";
        text_target.setText("English");
        text_explain.setText("Tiếng Việt");
    }

    @FXML
    private void handleSearchButton() {
        try {
            String word_target = Google_Translate_target.getText().toLowerCase();
            String word_explain = GoogleAPI.translate(word_target, language_target,
                    language_explain);
            Google_Translate_explain.setText(word_explain.substring(1, word_explain.length() - 1));
        } catch (IOException e) {
            Google_Translate_explain.setText("Kết nối Internet không ổn định!");
        }
    }

    @FXML
    private void handleSwapButton() {
        if (language_target == "en") {
            language_target = "vi";
            language_explain = "en";
            text_target.setText("Tiếng Việt");
            text_explain.setText("English");
        } else {
            language_target = "en";
            language_explain = "vi";
            text_target.setText("English");
            text_explain.setText("Tiếng Việt");
        }
    }
}
