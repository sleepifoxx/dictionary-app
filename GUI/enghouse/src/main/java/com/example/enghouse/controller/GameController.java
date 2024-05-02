package com.example.enghouse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameController extends Transition {
    @FXML
    private Button game_multiplechoice_button, game_mergeword_button;

    public Map<String, String> dictionary = new HashMap<>();
    public Map.Entry<String, String> questionEntry;

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
    private void handleMultipleChoiceButton() {
        handleTransition("MultipleChoice.fxml");
    }

    @FXML
    private void handleMergeWordButton() {
        handleTransition("MergeWord.fxml");
    }

}
