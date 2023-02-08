package com.example.clinica.utils.observer;

import com.example.clinica.utils.event.Event;

public interface Observable<E extends Event> {
    void addObserver(Observer<E> obs);
    void notifyObservers(E e);
}
