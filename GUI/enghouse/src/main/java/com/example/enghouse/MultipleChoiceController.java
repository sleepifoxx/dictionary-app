package com.example.enghouse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MultipleChoiceController {
    private Map<String, String> dictionary = new HashMap<>();
    private Map.Entry<String, String> randomEntry;
    private Map.Entry<String, String> questionEntry;
    private List<Map.Entry<String, String>> a = new ArrayList<>();
    private List<Map.Entry<String, String>> wordsList = new ArrayList<>();
    private int answerIndex;
    @FXML
    private Button ChoiceA, ChoiceB, ChoiceC, ChoiceD, NextButton, BackButton, alert_false, alert_true;
    @FXML
    private Text Word, text_choiceA, text_choiceB, text_choiceC, text_choiceD;
    @FXML
    private AnchorPane mainPane, contentAnchorPane;

    @FXML
    public void initialize() {
        alert_false.setVisible(false);
        alert_true.setVisible(false);
        NextButton.setVisible(false);
        Random rd = new Random();
        insertFromFile();
        wordsList = new ArrayList<>(dictionary.entrySet());
        for (int i = 0; i < 4; ++i) {
            randomEntry = wordsList.remove(rd.nextInt(wordsList.size()));
            a.add(randomEntry);
        }

        questionEntry = a.get(rd.nextInt(a.size()));
        Word.setText(questionEntry.getKey());
        text_choiceA.setText(a.get(0).getValue());
        text_choiceB.setText(a.get(1).getValue());
        text_choiceC.setText(a.get(2).getValue());
        text_choiceD.setText(a.get(3).getValue());
    }

    @FXML
    private void checkText() {
        if (a.get(answerIndex).equals(questionEntry)) {
            alert_true.setVisible(true);
            alert_true.setText("Chính xác!");
            ChoiceA.setDisable(true);
            ChoiceB.setDisable(true);
            ChoiceC.setDisable(true);
            ChoiceD.setDisable(true);
            NextButton.setVisible(true);
        } else {
            alert_false.setVisible(true);
            alert_false.setText("Sai rồi! Đáp án đúng là: " + questionEntry.getValue());
            ChoiceA.setDisable(true);
            ChoiceB.setDisable(true);
            ChoiceC.setDisable(true);
            ChoiceD.setDisable(true);
            NextButton.setVisible(true);
        }
    }

    @FXML
    private void handleBackButton() {
        loadFXML("Game.fxml");
    }

    @FXML
    private void handleChoiceAButton() {
        answerIndex = 0;
        checkText();
    }

    @FXML
    private void handleChoiceBButton() {
        answerIndex = 1;
        checkText();
    }

    @FXML
    private void handleChoiceCButton() {
        answerIndex = 2;
        checkText();
    }

    @FXML
    private void handleChoiceDButton() {
        answerIndex = 3;
        checkText();
    }

    @FXML
    private void handleNextButton() {
        Random rd = new Random();
        a = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            randomEntry = wordsList.remove(rd.nextInt(wordsList.size()));
            a.add(randomEntry);
        }
        questionEntry = a.get(rd.nextInt(a.size()));
        Word.setText(questionEntry.getKey());
        text_choiceA.setText(a.get(0).getValue());
        text_choiceB.setText(a.get(1).getValue());
        text_choiceC.setText(a.get(2).getValue());
        text_choiceD.setText(a.get(3).getValue());
        NextButton.setVisible(false);
        ChoiceA.setDisable(false);
        ChoiceB.setDisable(false);
        ChoiceC.setDisable(false);
        ChoiceD.setDisable(false);
        alert_true.setText("");
        alert_false.setText("");
        alert_true.setVisible(false);
        alert_false.setVisible(false);
    }

    public void insertFromFile() {
        try {
            Scanner scanner = new Scanner(new File("database/dictionaries_game.txt"));

            while (scanner.hasNextLine()) {
                String word_target = scanner.nextLine();
                String word_explain = scanner.nextLine();
                dictionary.put(word_target, word_explain);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @FXML
    private void loadFXML(String fxmlFileName) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxmlFileName));
            contentAnchorPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}