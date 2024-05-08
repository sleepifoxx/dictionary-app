package com.example.enghouse.controller;

import com.example.enghouse.model.AudioAPI;
import com.example.enghouse.model.Data;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
    @FXML
    private Button soundButton;

    private List<String> englishWords;
    private String filePath = "database/bookmark.txt";


    public void initialize() {
        loadEnglishWords();
        listView.getItems().addAll(englishWords);
        btnDelete.setVisible(false);
        soundButton.setVisible(false);
        textArea.setEditable(false);
    }
    @FXML
    private void handleRecentListSelection() {
        String selectedWord = listView.getSelectionModel().getSelectedItem();
        if (selectedWord != null) {
            VisibleTrue();
            String word_explain = Data.searchData(selectedWord);
            textArea.setText(word_explain);
        }
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
    @FXML
    private void handleSoundButton() {
        String selectedWord = listView.getSelectionModel().getSelectedItem();
        AudioAPI.AudioPlay(selectedWord ,"en-US");
    }
    @FXML
    private void VisibleTrue() {
        btnDelete.setVisible(true);
        soundButton.setVisible(true);
        textArea.setEditable(true);
    }
    @FXML
    private void VisibleFalse() {
        btnDelete.setVisible(false);
        soundButton.setVisible(false);
        textArea.setEditable(false);
    }
    @FXML
    private void handleDeleteButton() {
        String selectedWord = listView.getSelectionModel().getSelectedItem();
        if (selectedWord != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận xóa");
            alert.setHeaderText("Bạn có muốn xóa từ \"" + selectedWord + "\" khỏi danh sách?");
            alert.setContentText("Thao tác này không thể hoàn tác.");
            ButtonType okButton = new ButtonType("Xóa", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Hủy", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(okButton, cancelButton);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/com/example/enghouse/css/alert.css").toExternalForm());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                englishWords.remove(selectedWord);
                listView.getItems().remove(selectedWord);
                updateFileContent();
                textArea.setText("");
                VisibleFalse();
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Thông báo");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Đã xóa từ thành công");
                successAlert.showAndWait();
            }
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Lỗi");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Không có từ nào được chọn để xóa");
            errorAlert.showAndWait();
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