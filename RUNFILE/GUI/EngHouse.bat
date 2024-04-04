@echo off
java --module-path %~dp0/javafx/lib --add-modules javafx.controls,javafx.fxml -jar %~dp0/jar/enghouse.jar