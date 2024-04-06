package com.example.enghouse;

import java.io.*;
import java.util.*;

public class Data {
    private static Map<String, String> data = new TreeMap<>();
    public static List<String> suggestWordList = new ArrayList<>();

    public static void insertFromDictionaries() {
        try {
            FileReader fileReader = new FileReader("database/dictionaries.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String englishWord = bufferedReader.readLine();
            englishWord = englishWord.replace("|", "");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Word word = new Word("", "");
                word.setWord_target(englishWord);
                String meaning = line + "\n";
                while ((line = bufferedReader.readLine()) != null)
                    if (!line.startsWith("|"))
                        meaning += line + "\n";
                    else {
                        englishWord = line.replace("|", "");
                        break;
                    }
                word.setWord_explain(meaning);
                data.put(word.getWord_target(), word.getWord_explain());
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    public static void returnSuggestWord(String word) {
        word = word.toLowerCase();
        suggestWordList.clear();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (entry.getKey().startsWith(word)) {
                suggestWordList.add(entry.getKey());
            }
        }
        suggestWordList.sort(String::compareTo);
    }

    public static void saveData() {
        try {
            FileWriter fileWriter = new FileWriter("database/dictionaries.txt");
            for (Map.Entry<String, String> entry : data.entrySet()) {
                if (!entry.getValue().endsWith("\n")) {
                    entry.setValue(entry.getValue() + "\n");
                }
                fileWriter.write("|" + entry.getKey() + "\n" + entry.getValue());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        }
    }

    public static String searchData(String word) {
        return data.get(word);
    }

    public static boolean isWordExist(String word) {
        return data.containsKey(word);
    }

    public static void addWord(Word word) {
        data.put(word.getWord_target(), word.getWord_explain());
    }

    public static void removeWord(String word) {
        data.remove(word);
    }
}
