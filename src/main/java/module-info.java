module com.example.clinica {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.clinica to javafx.fxml;
    exports com.example.clinica;
    exports com.example.clinica.controller;
    opens com.example.clinica.controller to javafx.fxml;
    exports com.example.clinica.domain;
    opens com.example.clinica.domain to javafx.fxml;
}