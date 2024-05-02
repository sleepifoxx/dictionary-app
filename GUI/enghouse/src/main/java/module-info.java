module com.example.enghouse {
    requires javafx.controls;
    requires java.desktop;
    requires javafx.fxml;
    requires jlayer;

    opens com.example.enghouse to javafx.fxml;

    exports com.example.enghouse;
    exports com.example.enghouse.controller;
    opens com.example.enghouse.controller to javafx.fxml;
    exports com.example.enghouse.model;
    opens com.example.enghouse.model to javafx.fxml;
}