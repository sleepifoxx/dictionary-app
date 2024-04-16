package com.example.enghouse;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookmarkController {

    @FXML
    private ListView<String> listView;

    @FXML
    private TextArea textArea;

    @FXML
    private Button btnDelete;

    private List<String> englishWords;
    private String filePath = "database/bookmark.txt";

    public void initialize() {
        loadEnglishWords();
        listView.getItems().addAll(englishWords);

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            textArea.setText(Data.searchData(newValue));
        });

        btnDelete.setOnAction(event -> deleteSelectedItem());
    }

    private void loadEnglishWords() {
        englishWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                englishWords.add(line);
            }
        } catch (IOException e) {
            System.err.println("Không tìm thấy tệp " + filePath);
        }
    }

    private void deleteSelectedItem() {
        String selectedWord = listView.getSelectionModel().getSelectedItem();
        if (selectedWord != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận xóa");
            alert.setHeaderText("Bạn có muốn xóa từ " + selectedWord + " khỏi danh sách?");
            alert.setContentText("Thao tác này không thể hoàn tác.");

            ButtonType okButton = new ButtonType("Xóa");
            ButtonType cancelButton = new ButtonType("Hủy");
            alert.getButtonTypes().setAll(okButton, cancelButton);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == okButton) {
                englishWords.remove(selectedWord);
                listView.getItems().remove(selectedWord);
                updateFileContent();
            }
        }
    }

    private void updateFileContent() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (String word : englishWords) {
                writer.write(word);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi cập nhật tệp văn bản.");
        }
    }
}