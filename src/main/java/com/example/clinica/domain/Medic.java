package com.example.clinica.domain;

public class Medic extends Entity<Integer>{
    private int sectieID;
    private String nume;
    private int vechime;
    private boolean rezident;

    public Medic(int id, int sectieID, String nume, int vechime, boolean rezident) {
        this.id = id;
        this.sectieID = sectieID;
        this.nume = nume;
        this.vechime = vechime;
        this.rezident = rezident;
    }

    public int getSectieID() {
        return sectieID;
    }

    public String getNume() {
        return nume;
    }

    public int getVechime() {
        return vechime;
    }

    public boolean isRezident() {
        return rezident;
    }
}
