package com.example.clinica.domain;

import java.io.Serializable;

public class Entity<ID> implements Serializable {
    private final long serialVersionUID = 12312432423L;
    protected ID id;

    public ID getId() {
        return id;
    }
}
