package com.rentfashion.rentappfashion.application.service.observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlquilerEventPublisher {

    private final List<AlquilerObserver> observers;

    public AlquilerEventPublisher(List<AlquilerObserver> observers) {
        if (observers == null) {
            this.observers = Collections.emptyList();
        } else {
            this.observers = new ArrayList<>(observers);
        }
    }

    public void notifyObservers(AlquilerEvent event) {
        for (AlquilerObserver observer : observers) {
            observer.update(event);
        }
    }
}
