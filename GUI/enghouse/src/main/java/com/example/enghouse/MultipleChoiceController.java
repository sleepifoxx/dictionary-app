package com.example.enghouse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class MultipleChoiceController {
    private Map<String, String> dictionary = new HashMap<>();
    private Map.Entry<String, String> randomEntry;
    private Map.Entry<String, String> questionEntry;
    private List<Map.Entry<String, String>> a = new ArrayList<>();
    private List<Map.Entry<String, String>> wordsList = new ArrayList<>();
    private int answerIndex;
    @FXML
    private Button ChoiceA, ChoiceB, ChoiceC, ChoiceD, NextButton;
    @FXML
    private Text Word, text_choiceA, text_choiceB, text_choiceC, text_choiceD, alert;

    @FXML
    public void initialize() {
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
            alert.setText("Chính xác! Nhấn Next để chơi tiếp.");
            NextButton.setVisible(true);
        } else {
            alert.setText("Rất tiếc, bạn đã trả lời sai.");
        }
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
}