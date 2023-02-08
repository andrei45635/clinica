package com.example.clinica.controller;

import com.example.clinica.HelloApplication;
import com.example.clinica.domain.Medic;
import com.example.clinica.domain.Sectie;
import com.example.clinica.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    private Service service;
    private ObservableList<Sectie> sectieModel = FXCollections.observableArrayList();
    @FXML
    private TableView<Sectie> sectiiTableView;
    @FXML
    private TableColumn<Sectie, String> numeColumn;
    @FXML
    private TableColumn<Sectie, Integer> sefSectieIDColumn;
    @FXML
    private TableColumn<Sectie, Integer> pretColumn;
    @FXML
    private TableColumn<Sectie, Integer> durataConsultatieColumn;

    @FXML
    public void initialize(){
        numeColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
        sefSectieIDColumn.setCellValueFactory(new PropertyValueFactory<>("sefSectieID"));
        pretColumn.setCellValueFactory(new PropertyValueFactory<>("pret"));
        durataConsultatieColumn.setCellValueFactory(new PropertyValueFactory<>("durataConsultatie"));

        sectiiTableView.setItems(sectieModel);
    }

    public void setService(Service service) throws IOException {
        this.service = service;
        initModel();
        for(Medic medic: service.getAllMedici()){
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("medici-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            MediciController controller = fxmlLoader.getController();
            controller.setMedic(medic);
            controller.setService(service);
            stage.show();
        }
    }

    public void clicker(){
        var selectedSectie = sectiiTableView.getFocusModel().getFocusedItem();
        sectiiTableView.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../consultatie-view.fxml"));
                    Parent root;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ConsultatieController consultatieController = loader.getController();
                    consultatieController.setSelectedSectie(selectedSectie);
                    consultatieController.setService(service);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root, 600, 600));
                    stage.setTitle("Hello!");
                    stage.show();
                }
            }
        });
    }

    public void initModel(){
        sectieModel.setAll(service.getAllSectii());
        clicker();
    }
}