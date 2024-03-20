package com.example.enghouse;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class EditController {
    private String word_target, word_explain;

    @FXML
    private Button home_search_button, home_button, google_translate_button;

    @FXML
    private TextField edit_word_target;

    @FXML
    private TextArea edit_word_explain;

    @FXML
    public void handleCheckButton() {
        String word_target = edit_word_target.getText();
        if (Data.isWordExist(word_target)) {
            edit_word_explain.setText(Data.searchData(word_target));
        }
    }

    @FXML
    public void handleSaveButton() {
        word_target = edit_word_target.getText();
        word_explain = edit_word_explain.getText();
        Word word = new Word(word_target, word_explain);
        Data.addWord(word);
    }

    // Menu
    // Menu
    @FXML
    public void handleHomeButton() {
        App.changeScene("Home.fxml", (Stage) home_button.getScene().getWindow());
    }

    @FXML
    public void handleGoogleTranslateButton() {
        App.changeScene("GoogleTranslate.fxml", (Stage) home_search_button.getScene().getWindow());
    }

    @FXML
    public void handleEditButton() {
        App.changeScene("Edit.fxml", (Stage) home_search_button.getScene().getWindow());
    }
}
