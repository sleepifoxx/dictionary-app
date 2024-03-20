package com.example.enghouse;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class EditController {
    private String word_target, word_explain;

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

}
