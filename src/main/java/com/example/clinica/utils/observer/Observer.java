package com.example.clinica.utils.observer;

import com.example.clinica.utils.event.Event;

public interface Observer<E extends Event> {
    void update(E e);
}
