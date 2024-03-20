package com.example.enghouse;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class HomeController {
    @FXML
    private TextField home_search_bar;
    @FXML
    private TextArea home_explain_area;

    @FXML
    public void search() {
        String word_target = home_search_bar.getText();
        String word_explain = Data.searchData(word_target);
        home_explain_area.setText(word_explain);
    }

}
