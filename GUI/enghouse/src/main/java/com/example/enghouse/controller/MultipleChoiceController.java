package com.example.enghouse.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class MultipleChoiceController extends GameController {
    private Map.Entry<String, String> randomEntry;
    private List<Map.Entry<String, String>> a = new ArrayList<>();
    private List<Map.Entry<String, String>> wordsList = new ArrayList<>();
    private int answerIndex;
    @FXML
    private Button ChoiceA, ChoiceB, ChoiceC, ChoiceD, NextButton, BackButton, alert_false, alert_true;
    @FXML
    private Text Word, text_choiceA, text_choiceB, text_choiceC, text_choiceD;

    @FXML
    public void initialize() {
        a.clear();
        nextShow(false);
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
            alert_true.setText("Chính xác!");
            alert_true.setVisible(true);
            nextShow(true);
        } else {
            alert_false.setText("Sai rồi! Đáp án đúng là: " + questionEntry.getValue());
            alert_false.setVisible(true);
            nextShow(true);
        }
    }

    @FXML
    private void nextShow(boolean tf) {
        if (tf == false) {
            alert_true.setText("");
            alert_false.setText("");
            alert_true.setVisible(tf);
            alert_false.setVisible(tf);
        }
        ChoiceA.setDisable(tf);
        ChoiceB.setDisable(tf);
        ChoiceC.setDisable(tf);
        ChoiceD.setDisable(tf);
        NextButton.setVisible(tf);
    }

    @FXML
    private void handleBackButton() {
        handleTransition("Game.fxml");
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
        initialize();
    }

}