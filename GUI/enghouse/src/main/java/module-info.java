module com.example.enghouse {
    requires javafx.controls;
    requires java.desktop;
    requires javafx.fxml;
    requires jlayer;

    opens com.example.enghouse to javafx.fxml;

    exports com.example.enghouse;
}