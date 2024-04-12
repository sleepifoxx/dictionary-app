package com.example.enghouse;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.awt.Desktop;
import java.net.URI;
import java.io.IOException;

public class AboutController {
    @FXML
    private Button githubButton1, githubButton2, githubButton3, facebookButton1, facebookButton2, facebookButton3;

    @FXML
    private void handlegithubButton1() {
        try {
            Desktop.getDesktop().browse(URI.create("https://github.com/sleepifoxx"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handlegithubButton2() {
        try {
            Desktop.getDesktop().browse(URI.create("https://github.com/quang011105"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handlegithubButton3() {
        try {
            Desktop.getDesktop().browse(URI.create("https://github.com/toivuvan"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleFacebookButton1() {
        try {
            Desktop.getDesktop().browse(URI.create("https://web.facebook.com/sleepi.foxx/"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleFacebookButton2() {
        try {
            Desktop.getDesktop().browse(URI.create("https://web.facebook.com/profile.php?id=100070916686524"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleFacebookButton3() {
        try {
            Desktop.getDesktop().browse(URI.create("https://web.facebook.com/profile.php?id=100053284971289"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
