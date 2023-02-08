package com.example.clinica;

import com.example.clinica.controller.HelloController;
import com.example.clinica.repository.db.ConsultatieRepoDB;
import com.example.clinica.repository.db.MedicRepoDB;
import com.example.clinica.repository.db.SectieRepoDB;
import com.example.clinica.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        HelloController helloController = fxmlLoader.getController();
        helloController.setService(new Service(new SectieRepoDB(), new MedicRepoDB(), new ConsultatieRepoDB()));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}