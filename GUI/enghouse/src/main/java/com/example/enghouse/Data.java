package com.example.enghouse;

import java.io.*;
import java.util.*;

public class Data {
    private static Map<String, Word> data = new HashMap<>();

    public static void readData() throws IOException {
        FileReader fis = new FileReader("database/E_V.txt");
        BufferedReader br = new BufferedReader(fis);
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("<html>");
            String word = parts[0];
            String definition = "<html>" + parts[1];
            Word wordObj = new Word(word, definition);
            data.put(word, wordObj);
        }
    }

    public static void searchData(String word) {
        Word wordObj = data.get(word);
        if (wordObj != null) {
            System.out.println(wordObj.getDef());
        } else {
            System.out.println("Word not found");
        }
    }
}

class Word {
    private String word;
    private String def;

    public Word(String word, String def) {
        this.word = word;
        this.def = def;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }
}