package com.example.enghouse.controller;

import com.example.enghouse.App;
import com.example.enghouse.model.Data;
import com.example.enghouse.model.Word;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

public class EditController {
    private String word_target, word_explain;

    @FXML
    private TextField edit_word_target;
    @FXML
    private Button edit_check_button, edit_save_button, edit_alert, edit_remove_button;
    @FXML
    private TextArea edit_word_explain;

    @FXML
    private void initialize() {
        edit_save_button.setDisable(true);
        edit_alert.setVisible(false);
        edit_remove_button.setVisible(false);
        edit_word_target.setText("");
        edit_word_explain.setText("");
        edit_word_explain.setDisable(true);
    }

    @FXML
    private void handleCheckButton() {
        String word_target = edit_word_target.getText();
        edit_save_button.setDisable(false);
        if (Data.isWordExist(word_target)) {
            App.Alert(edit_alert, "Từ đã có trong từ điển!", 2);
            edit_remove_button.setVisible(true);
            edit_word_explain.setDisable(false);
            edit_word_explain.setText(Data.searchData(word_target));
        } else {
            App.Alert(edit_alert, "Từ chưa có trong từ điển!", 2);
            edit_remove_button.setVisible(true);
            edit_word_explain.setDisable(false);
            edit_word_explain.setText("");
        }
    }

    @FXML
    private void handleSaveButton() {
        App.Alert(edit_alert, "Lưu từ thành công!", 2);
        word_target = edit_word_target.getText().toLowerCase();
        word_explain = edit_word_explain.getText();
        edit_remove_button.setVisible(false);
        edit_word_explain.setDisable(true);
        edit_save_button.setDisable(true);
        edit_word_explain.setText("");
        edit_word_target.setText("");
        Word word = new Word(word_target, word_explain);
        Data.addWord(word);
    }

    @FXML
    private void handleRemoveButton() {
        word_target = edit_word_target.getText().toLowerCase();
        Data.removeWord(word_target);
        App.Alert(edit_alert, "Xoá từ thành công!", 2);
        edit_word_target.setText("");
        edit_word_explain.setText("");
        edit_remove_button.setVisible(false);
        edit_word_explain.setDisable(true);
        edit_save_button.setDisable(true);
    }
}
