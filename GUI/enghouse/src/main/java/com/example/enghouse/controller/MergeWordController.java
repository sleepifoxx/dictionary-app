package com.example.enghouse.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class MergeWordController extends GameController {
    private List<Map.Entry<String, String>> wordsList = new ArrayList<>();
    private String word_target;
    private char[] word_target_array;

    @FXML
    private Text printWord, Answer_Exactly;
    @FXML
    private TextField inputWord;
    @FXML
    private Button NextButton, Enter, CheckAnswer, Backbutton, true_button, false_button;
    @FXML
    private AnchorPane mainPane, contentAnchorPane;

    @FXML
    public void initialize() {
        Answer_Exactly.setText("");
        inputWord.setText("");
        true_button.setText("");
        false_button.setText("");
        true_button.setVisible(false);
        false_button.setVisible(false);
        NextButton.setVisible(false);
        Random rd = new Random();
        insertFromFile();
        wordsList = new ArrayList<>(dictionary.entrySet());
        questionEntry = wordsList.remove(rd.nextInt(wordsList.size()));
        word_target = questionEntry.getKey();
        word_target_array = word_target.toCharArray();
        for (int i = 0; i < word_target_array.length; i++) {
            int j = rd.nextInt(word_target_array.length);
            char temp = word_target_array[i];
            word_target_array[i] = word_target_array[j];
            word_target_array[j] = temp;
        }
        String s = "/ ";
        for (int i = 0; i < word_target_array.length; i++) {
            s += word_target_array[i];
            s += " / ";
        }
        printWord.setText(s);
    }

    @FXML
    private void checkText() {
        if (inputWord.getText().equals(word_target)) {
            true_button.setVisible(true);
            true_button.setText("Chính xác!");
            NextButton.setVisible(true);
            NextButton.setVisible(true);
        } else {
            false_button.setVisible(true);
            false_button.setText("Sai rồi! Hãy thử lại.");
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> false_button.setVisible(false));
            pause.play();

        }
    }

    @FXML
    private void handleBackButton() {
        handleTransition("Game.fxml");
    }

    @FXML
    private void AnswerExactly() {
        Answer_Exactly.setText(word_target + ": " + questionEntry.getValue());
    }

    @FXML
    private void handleNextButton() {
        initialize();
    }

}
