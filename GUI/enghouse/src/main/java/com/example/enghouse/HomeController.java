package com.example.enghouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class HomeController {
    private static List<String> recent = new ArrayList<>();
    private String selectedWord;
    @FXML
    private TextField home_search_bar;
    @FXML
    private TextArea home_explain_area;
    @FXML
    private Button home_search_button, home_reset_recent_button, home_remove_button, home_edit_button, home_save_button;
    @FXML
    private ListView<String> home_recent_list, home_suggestWord_list;
    @FXML
    private Text home_alert;

    @FXML
    private void initialize() {
        home_recent_list.getItems().clear();
        recent.clear();
        insertfromRecent();
        home_reset_recent_button.setDisable(true);
        home_explain_area.setEditable(false);
        home_save_button.setVisible(false);
        home_edit_button.setVisible(false);
        home_remove_button.setVisible(false);
        suggestWordListExited();
        if (recent.size() > 0) {
            home_reset_recent_button.setDisable(false);
            for (String word : recent) {
                home_recent_list.getItems().add(word);
            }
        }
    }

    @FXML
    private void handleSuggestWordList() {
        String word = home_search_bar.getText();
        home_suggestWord_list.setVisible(true);
        Data.returnSuggestWord(word);
        home_suggestWord_list.getItems().clear();
        for (String suggestWord : Data.suggestWordList) {
            home_suggestWord_list.getItems().add(suggestWord);
        }
    }

    @FXML
    private void handleSuggestWordSelection() {
        selectedWord = home_suggestWord_list.getSelectionModel().getSelectedItem();
        home_search_bar.setText(selectedWord);
        handleSearchButton();
        suggestWordListExited();
        home_edit_button.setVisible(true);
        home_remove_button.setVisible(true);
    }

    @FXML
    private void handleRemoveButton() {
        String word_target = home_search_bar.getText();
        if (Data.isWordExist(word_target) == false) {
            Alert(word_target + " not found!");
        } else {
            Data.removeWord(word_target);
            Alert("Word removed successfully!");
            home_search_bar.setText("");
            home_explain_area.setText("");
            home_edit_button.setVisible(false);
            home_remove_button.setVisible(false);
            recent.remove(word_target);
            home_recent_list.getItems().clear();
            for (String word : recent) {
                home_recent_list.getItems().add(word);
            }
        }
    }

    @FXML
    private void handleEditButton() {
        String word_target = home_search_bar.getText();
        if (Data.isWordExist(word_target) == false) {
            Alert(word_target + " not found!");
        } else {
            home_explain_area.setEditable(true);
            home_save_button.setVisible(true);
        }
    }

    @FXML
    private void handleSaveButton() {
        String word_target = home_search_bar.getText();
        String word_explain = home_explain_area.getText();
        if (Data.isWordExist(word_target) == false) {
            Alert(word_target + " not found!");
        } else {
            Word word = new Word(word_target, word_explain);
            Data.addWord(word);
            Alert("Word edited successfully!");
            home_explain_area.setEditable(false);
            home_save_button.setVisible(false);
        }
    }

    @FXML
    private void Alert(String massage) {
        home_alert.setText(massage);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            home_alert.setText("");
        }));
        timeline.play();
    }

    @FXML
    private void suggestWordListExited() {
        home_suggestWord_list.setVisible(false);
    }

    @FXML
    private void handleRecentListSelection() {
        selectedWord = home_recent_list.getSelectionModel().getSelectedItem();
        reloadRecentList(selectedWord);
        home_search_bar.setText(selectedWord);
        String word_explain = Data.searchData(selectedWord);
        home_explain_area.setText(word_explain);
        home_edit_button.setVisible(true);
        home_remove_button.setVisible(true);
        home_suggestWord_list.setVisible(false);
    }

    @FXML
    private void handleSearchButton() {
        String word_target = home_search_bar.getText();
        suggestWordListExited();
        if (Data.isWordExist(word_target) == false) {
            home_alert.setText(word_target + " not found!");
            home_explain_area.setText("");
        } else {
            home_alert.setText("");
            String word_explain = Data.searchData(word_target);
            home_explain_area.setText(word_explain);
            if (!recent.contains(word_target)) {
                reloadRecentList(word_target);
                home_reset_recent_button.setDisable(false);
            } else {
                reloadRecentList(word_target);
            }
            home_edit_button.setVisible(true);
            home_remove_button.setVisible(true);
        }
    }

    @FXML
    private void reloadRecentList(String word) {
        home_save_button.setVisible(false);
        home_explain_area.setEditable(false);
        recent.remove(word);
        recent.add(0, word);
        home_recent_list.getItems().clear();
        for (String temp : recent) {
            home_recent_list.getItems().add(temp);
        }
    }

    @FXML
    private void handleResetRecentButton() {
        home_reset_recent_button.setDisable(true);
        recent.clear();
        home_recent_list.getItems().clear();
    }

    // File recent.txt Controller
    // Write data to recent.txt
    public static void saveRecent() {
        try {
            PrintWriter writer = new PrintWriter(new File("database/recent.txt"));
            for (String word : recent) {
                writer.println(word);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        }
    }

    // Read data from recent.txt
    public static void insertfromRecent() {
        try {
            Scanner scanner = new Scanner(new File("database/recent.txt"));
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                recent.add(word);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        }
    }

}
