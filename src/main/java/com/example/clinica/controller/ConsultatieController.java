package com.example.clinica.controller;

import com.example.clinica.domain.Medic;
import com.example.clinica.domain.Sectie;
import com.example.clinica.service.Service;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Time;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ConsultatieController {
    @FXML
    private TextField idColumn;
    @FXML
    private ComboBox<String> medicComboBox;
    @FXML
    private TextField cnpColumn;
    @FXML
    private TextField numeColumn;
    @FXML
    private DatePicker dataPicker;
    @FXML
    private Spinner<Integer> oraSpinner;
    private Service service;
    private Sectie selectedSectie;

    public void setSelectedSectie(Sectie sectie){
        this.selectedSectie = sectie;
    }
    public void setService(Service service){
        this.service = service;
        Set<String> cct = service.getAllMediciNume(selectedSectie.getId());
        medicComboBox.setItems(FXCollections.observableArrayList(cct));
    }

    @FXML
    private void onClickProgramare(ActionEvent actionEvent) {
        for(Medic medic: service.getAllMedici()){
            if(Objects.equals(medic.getNume(), medicComboBox.getValue())){
                service.addConsultatie(Integer.parseInt(idColumn.getText()), medic.getId(), Integer.parseInt(cnpColumn.getText()), numeColumn.getText(), dataPicker.getValue(), Time.valueOf(oraSpinner.getValue() + ":00:00"));
            }
        }
    }
}
