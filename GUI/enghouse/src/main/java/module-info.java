module com.example.enghouse {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.enghouse to javafx.fxml;

    exports com.example.enghouse;
}