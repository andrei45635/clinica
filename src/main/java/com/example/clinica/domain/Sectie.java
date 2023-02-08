package com.example.clinica.domain;

public class Sectie extends Entity<Integer>{
    private String nume;
    private int sefSectieID;
    private int pret;
    private int durataConsultatie;

    public Sectie(int id, String nume, int sefSectieID, int pret, int durataConsultatie) {
        this.id = id;
        this.nume = nume;
        this.sefSectieID = sefSectieID;
        this.pret = pret;
        this.durataConsultatie = durataConsultatie;
    }

    public String getNume() {
        return nume;
    }

    public int getSefSectieID() {
        return sefSectieID;
    }

    public int getPret() {
        return pret;
    }

    public int getDurataConsultatie() {
        return durataConsultatie;
    }
}
