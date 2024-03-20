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
    private Button menu_edit_button, menu_home_button, menu_bookmark_button, menu_game_button,
            menu_google_translate_button, menu_about_button;
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
        App.changeScene("Home.fxml", (Stage) menu_home_button.getScene().getWindow());
    }

    @FXML
    public void handleGoogleTranslateButton() {
        App.changeScene("GoogleTranslate.fxml", (Stage) menu_google_translate_button.getScene().getWindow());
    }

    @FXML
    public void handleEditButton() {
        App.changeScene("Edit.fxml", (Stage) menu_edit_button.getScene().getWindow());
    }

    @FXML
    public void handleBookmarkButton() {
        App.changeScene("Bookmark.fxml", (Stage) menu_bookmark_button.getScene().getWindow());
    }

    @FXML
    public void handleGameButton() {
        App.changeScene("Game.fxml", (Stage) menu_game_button.getScene().getWindow());
    }

    @FXML
    public void handleAboutButton() {
        App.changeScene("About.fxml", (Stage) menu_about_button.getScene().getWindow());
    }
}
