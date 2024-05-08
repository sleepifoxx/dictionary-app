package com.example.enghouse.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.enghouse.App;
import com.example.enghouse.model.AudioAPI;
import com.example.enghouse.model.Data;
import com.example.enghouse.model.Word;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class HomeController {
    private static List<String> recent = new ArrayList<>();
    private static List<String> bookmark = new ArrayList<>();
    @FXML
    private TextField home_search_bar;
    @FXML
    private TextArea home_explain_area;
    @FXML
    private Button home_search_button, home_reset_recent_button, home_remove_button, home_edit_button, home_save_button,
            home_alert, home_sound_button, home_bookmark_button;
    @FXML
    private ListView<String> home_recent_list, home_suggestWord_list;
    @FXML
    private Text home_word_target;

    @FXML
    private void initialize() {
        if (recent.size() != 0) {
            saveRecent();
        }
        home_recent_list.getItems().clear();
        recent.clear();
        insertfromRecent();

        home_word_target.setVisible(false);
        home_reset_recent_button.setDisable(true);
        home_explain_area.setEditable(false);
        home_save_button.setVisible(false);
        home_edit_button.setVisible(false);
        home_bookmark_button.setVisible(false);
        home_remove_button.setVisible(false);
        home_sound_button.setVisible(false);
        home_alert.setVisible(false);
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
        Data.returnSuggestWord(word, home_suggestWord_list);
    }

    @FXML
    private void handleSuggestWordSelection() {
        String selectedWord = home_suggestWord_list.getSelectionModel().getSelectedItem();
        home_search_bar.setText(selectedWord);
        handleSearchButton();
        suggestWordListExited();
        home_word_target.setText(selectedWord);
        VisibleTrue(selectedWord);
    }

    @FXML
    private void handleRemoveButton() {
        String word_target = home_search_bar.getText().toLowerCase();
        Data.removeWord(word_target);
        App.Alert(home_alert, "Xoá từ thành công!", 2);
        home_search_bar.setText("");
        home_explain_area.setText("");
        VisibleFalse();
        recent.remove(word_target);
        home_recent_list.getItems().clear();
        for (String word : recent) {
            home_recent_list.getItems().add(word);
        }
    }

    @FXML
    private void handleEditButton() {
        String word_target = home_search_bar.getText().toLowerCase();
        if (Data.isWordExist(word_target) == false) {
            App.Alert(home_alert, "Không tìm thấy từ: " + word_target + "!", 2);
        } else {
            home_explain_area.setEditable(true);
            home_save_button.setVisible(true);
        }
    }

    @FXML
    private void handleSoundButton() {
        String word_target = home_search_bar.getText().toLowerCase();
        AudioAPI.AudioPlay(word_target, "en-US");
    }

    @FXML
    private void handleSaveButton() {
        String word_target = home_search_bar.getText().toLowerCase();
        String word_explain = home_explain_area.getText();
        Word word = new Word(word_target, word_explain);
        Data.addWord(word);
        App.Alert(home_alert, "Lưu từ thành công!", 2);
        home_explain_area.setEditable(false);
        home_save_button.setVisible(false);
    }

    @FXML
    private void handleBookmarkButton() {
        String word_target = home_search_bar.getText().toLowerCase();
        bookmark.add(word_target);
        addTextToFile("database/bookmark.txt", word_target);
        App.Alert(home_alert, "Thêm từ vào Bookmark thành công!", 2);
        home_bookmark_button.setVisible(false);
    }

    @FXML
    private void suggestWordListExited() {
        home_suggestWord_list.setVisible(false);
    }

    @FXML
    private void handleRecentListSelection() {
        String selectedWord = home_recent_list.getSelectionModel().getSelectedItem();
        if (selectedWord != null) {
            reloadRecentList(selectedWord);
            home_search_bar.setText(selectedWord);
            String word_explain = Data.searchData(selectedWord);
            home_explain_area.setText(word_explain);
            home_suggestWord_list.setVisible(false);
            home_word_target.setText(selectedWord);
            VisibleTrue(selectedWord);
        }
    }

    @FXML
    private void handleSearchButton() {
        String word_target = "";
        word_target = home_search_bar.getText().toLowerCase();
        suggestWordListExited();
        if (Data.isWordExist(word_target) == false) {
            App.Alert(home_alert, "Không tìm thấy từ: " + word_target + "!", 2);
            home_explain_area.setText("");
            VisibleFalse();
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
            home_word_target.setText(word_target);
            VisibleTrue(word_target);
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
        saveRecent();
    }

    @FXML
    private void VisibleFalse() {
        home_bookmark_button.setVisible(false);
        home_edit_button.setVisible(false);
        home_remove_button.setVisible(false);
        home_sound_button.setVisible(false);
        home_word_target.setVisible(false);
    }

    @FXML
    private void VisibleTrue(String word) {
        if (checkWordExists("database/bookmark.txt", word)) {
            home_bookmark_button.setVisible(false);
        } else {
            home_bookmark_button.setVisible(true);
        }
        home_edit_button.setVisible(true);
        home_remove_button.setVisible(true);
        home_sound_button.setVisible(true);
        home_word_target.setVisible(true);
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

    public static void addTextToFile(String filePath, String word) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            List<String> lines = new ArrayList<>();
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                lines.add(currentLine);
            }
            reader.close();

            lines.add(0, word);

            PrintWriter writer = new PrintWriter(new FileWriter(filePath));
            for (String line : lines) {
                writer.println(line);
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Có lỗi xảy ra: " + e.getMessage());
        }
    }

    public static boolean checkWordExists(String filePath, String targetWord) {
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(targetWord)) {
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Lỗi: Tệp không tồn tại hoặc không thể đọc.");
        }
        return false;
    }
}
