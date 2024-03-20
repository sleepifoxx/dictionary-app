package com.example.enghouse;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class HomeController {
    @FXML
    private TextField home_search_bar;
    @FXML
    private Button home_search_button, home_button, google_translate_button;
    @FXML
    private TextArea home_explain_area;

    @FXML
    public void search() {
        String word_target = home_search_bar.getText();
        String word_explain = Data.searchData(word_target);
        home_explain_area.setText(word_explain);
    }

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
