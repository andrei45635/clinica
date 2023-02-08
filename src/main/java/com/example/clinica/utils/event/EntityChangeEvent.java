package com.example.clinica.utils.event;

import com.example.clinica.domain.Consultatie;

public class EntityChangeEvent implements Event{
    private ChangeEventType type;
    private Consultatie data;
    private Consultatie new_data;

    public EntityChangeEvent(ChangeEventType type, Consultatie new_data) {
        this.type = type;
        this.new_data = new_data;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Consultatie getData() {
        return data;
    }

    public Consultatie getNew_data() {
        return new_data;
    }
}
