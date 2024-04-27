package com.example.enghouse;

import com.example.enghouse.controller.HomeController;
import com.example.enghouse.model.Data;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                App.class.getResource("/com/example/enghouse/views/Loading.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 640);
        stage.setTitle("EngHouse - Your English Learning Assistant");
        stage.getIcons().add(new Image(App.class.getResource("icons/dictionary.png").toExternalForm()));
        stage.setScene(scene);
        stage.show();

        Data.insertFromDictionaries();

        // When close the app, save all to file
        stage.setOnCloseRequest(e -> {
            Data.saveData();
            HomeController.saveRecent();
            // HomeController.saveBookmark();
        });
    }

    public static void Alert(Button alert_location, String massage, int seconds) {
        try {
            alert_location.setVisible(true);
            alert_location.setText(massage);
            Font quicksand = Font.loadFont(new FileInputStream(new File("quicksand.ttf")), 12);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(seconds), event -> {
                alert_location.setText("");
                alert_location.setFont(quicksand);
                alert_location.setVisible(false);
            }));
            timeline.play();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static void main(String[] args) {
        launch();
    }

}
