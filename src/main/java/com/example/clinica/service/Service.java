package com.example.clinica.service;

import com.example.clinica.domain.Consultatie;
import com.example.clinica.domain.Medic;
import com.example.clinica.domain.Sectie;
import com.example.clinica.repository.db.ConsultatieRepoDB;
import com.example.clinica.repository.db.MedicRepoDB;
import com.example.clinica.repository.db.SectieRepoDB;
import com.example.clinica.utils.event.ChangeEventType;
import com.example.clinica.utils.event.EntityChangeEvent;
import com.example.clinica.utils.observer.Observable;
import com.example.clinica.utils.observer.Observer;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Service implements Observable<EntityChangeEvent> {
    private SectieRepoDB sectieRepoDB;
    private MedicRepoDB medicRepoDB;
    private ConsultatieRepoDB consultatieRepoDB;
    private List<Observer<EntityChangeEvent>> observers = new ArrayList<>();

    public Service(SectieRepoDB sectieRepoDB, MedicRepoDB medicRepoDB, ConsultatieRepoDB consultatieRepoDB) {
        this.sectieRepoDB = sectieRepoDB;
        this.medicRepoDB = medicRepoDB;
        this.consultatieRepoDB = consultatieRepoDB;
    }

    public List<Sectie> getAllSectii(){
        return sectieRepoDB.findAll();
    }

    public List<Medic> getAllMedici(){
        return medicRepoDB.findAll();
    }

    public List<Consultatie> getAllConsultatii(){
        return consultatieRepoDB.findAll();
    }

    public Set<String> getAllMediciNume(int sectieID){
        Set<String> medici = new HashSet<>();
        medicRepoDB.getMediciSectie(sectieID).forEach(m -> medici.add(m.getNume()));
        return medici;
    }

    public void addConsultatie(int id, int medicID, int cnp, String nume, LocalDate data, Time ora){
        Consultatie consultatie = new Consultatie(id, medicID, cnp, nume, data, ora);
        consultatieRepoDB.save(consultatie);
        notifyObservers(new EntityChangeEvent(ChangeEventType.ADD, consultatie));
    }

    @Override
    public void addObserver(Observer<EntityChangeEvent> obs) {
        observers.add(obs);
    }

    @Override
    public void notifyObservers(EntityChangeEvent entityChangeEvent) {
        observers.forEach(x -> x.update(entityChangeEvent));
    }
}
