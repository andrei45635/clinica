package com.example.clinica.controller;

import com.example.clinica.domain.Consultatie;
import com.example.clinica.domain.Medic;
import com.example.clinica.service.Service;
import com.example.clinica.utils.event.EntityChangeEvent;
import com.example.clinica.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MediciController implements Observer<EntityChangeEvent> {
    @FXML
    private TableView<Consultatie> consultatieTableView;
    @FXML
    private TableColumn<Consultatie, String> numeColumn;
    @FXML
    private TableColumn<Consultatie, Integer> cnpColumn;
    @FXML
    private TableColumn<Consultatie, LocalDate> dataColumn;
    @FXML
    private TableColumn<Consultatie, Time> oraColumn;
    private Service service;
    private ObservableList<Consultatie> consultatieModel = FXCollections.observableArrayList();
    private Medic loggedInMedic;

    @FXML
    public void initialize(){
        numeColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
        cnpColumn.setCellValueFactory(new PropertyValueFactory<>("CNP"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        oraColumn.setCellValueFactory(new PropertyValueFactory<>("ora"));

        consultatieTableView.setItems(consultatieModel);
    }

    public void setService(Service service){
        this.service = service;
        service.addObserver(this);
        initModel();
    }

    public void setMedic(Medic medic){
        this.loggedInMedic = medic;
    }

    @Override
    public void update(EntityChangeEvent entityChangeEvent) {
        initModel();
    }

    private void initModel() {
        List<Consultatie> consultatii = new ArrayList<>();
        for(Consultatie consultatie: service.getAllConsultatii()){
            if(consultatie.getMedicID() == loggedInMedic.getId()){
                consultatii.add(consultatie);
            }
        }
        consultatieModel.setAll(consultatii);
    }
}
