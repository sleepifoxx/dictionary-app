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

public class HomeController {
    private static List<String> recent = new ArrayList<>();
    private String selectedWord;
    private File recentFile = new File("database/recent.txt");
    @FXML
    private TextField home_search_bar;
    @FXML
    private TextArea home_explain_area;
    @FXML
    private Button home_search_button, home_reset_recent_button;
    @FXML
    private ListView<String> home_recent_list;

    @FXML
    private void initialize() {
        home_reset_recent_button.setDisable(true);
        insertfromRecent();
        if (recent.size() > 0) {
            home_reset_recent_button.setDisable(false);
            for (String word : recent) {
                home_recent_list.getItems().add(word);
            }
        }
    }

    @FXML
    private void handleRecentListSelection() {
        selectedWord = home_recent_list.getSelectionModel().getSelectedItem();
        home_search_bar.setText(selectedWord);
        String word_explain = Data.searchData(selectedWord);
        home_explain_area.setText(word_explain);

    }

    @FXML
    private void handleSearchButton() {
        String word_target = home_search_bar.getText();
        String word_explain = Data.searchData(word_target);
        home_explain_area.setText(word_explain);
        if (!recent.contains(word_target)) {
            recent.add(word_target);
            home_recent_list.getItems().add(word_target);
            home_reset_recent_button.setDisable(false);
            exporttoRecent();
        }
    }

    @FXML
    private void handleResetRecentButton() {
        home_reset_recent_button.setDisable(true);
        Data.clearFile("database/recent.txt");
        recent.clear();
        home_recent_list.getItems().clear();
    }

    // File recent.txt Controller
    // Write data to recent.txt
    private void exporttoRecent() {
        try {
            PrintWriter writer = new PrintWriter(recentFile);
            for (String word : recent) {
                writer.println(word);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        }
    }

    // Read data from recent.txt
    private void insertfromRecent() {
        try {
            Scanner scanner = new Scanner(recentFile);
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
