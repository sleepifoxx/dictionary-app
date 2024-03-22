package com.example.enghouse;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.io.IOException;

public class GoogleTranslateController {

    @FXML
    private TextArea Google_Translate_explain;

    @FXML
    private TextArea Google_Translate_target;

    @FXML
    public void Google_Translate_Search() throws IOException {
        String word_target = Google_Translate_target.getText().toLowerCase();
        String word_explain = GoogleAPI.translate(word_target, GoogleAPI.LANGUAGE.ENGLISH,
                GoogleAPI.LANGUAGE.VIETNAMESE);
        Google_Translate_explain.setText(word_explain.substring(1, word_explain.length() - 1));
    }
}
