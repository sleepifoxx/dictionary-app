package com.example.enghouse;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class GoogleTranslateController {

    @FXML
    private TextArea Google_Translate_explain;

    @FXML
    private TextArea Google_Translate_target;

    @FXML
    public void Google_Translate_Search() {
        String word_target = Google_Translate_target.getText().toLowerCase();
        String word_explain = GoogleAPI.translate("en", "vi", word_target);
        Google_Translate_explain.setText(word_explain);
    }
}
