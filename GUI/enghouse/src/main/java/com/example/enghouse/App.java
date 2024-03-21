package com.example.enghouse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 640);
        stage.setTitle("EngHouse - Your English Learning Assistant");
        stage.getIcons().add(new Image(App.class.getResource("icons/dictionary.png").toExternalForm()));
        stage.setScene(scene);
        stage.show();

        Data.insertFromDictionaries();
    }

    public static void main(String[] args) {
        launch();
    }

}
