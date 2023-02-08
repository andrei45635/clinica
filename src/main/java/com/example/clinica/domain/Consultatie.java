package com.example.clinica.domain;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Consultatie extends Entity<Integer>{
    private int medicID;
    private int CNP;
    private String nume;
    private LocalDate data;
    private Time ora;

    public Consultatie(int id, int medicID, int CNP, String nume, LocalDate data, Time ora) {
        this.id = id;
        this.medicID = medicID;
        this.CNP = CNP;
        this.nume = nume;
        this.data = data;
        this.ora = ora;
    }

    public int getMedicID() {
        return medicID;
    }

    public int getCNP() {
        return CNP;
    }

    public String getNume() {
        return nume;
    }

    public LocalDate getData() {
        return data;
    }

    public Time getOra() {
        return ora;
    }
}
