package com.example.enghouse;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class AboutController {
    @FXML
    private Button menu_edit_button, menu_home_button, menu_bookmark_button, menu_game_button,
            menu_google_translate_button, menu_about_button;

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
