package com.example.enghouse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 640);
        stage.setTitle("EngHouse - Your English Learning Assistant");
        stage.setScene(scene);
        stage.show();

        Data.readData();
    }

    public static void main(String[] args) {
        launch();
    }
}
