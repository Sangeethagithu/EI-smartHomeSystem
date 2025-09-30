package com.smarthome.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private final List<DeviceObserver> observers = new ArrayList<>();

    public void addObserver(DeviceObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(DeviceObserver observer) {
        observers.remove(observer);
    }

    protected void notifyObservers() {
        for (DeviceObserver observer : observers) {
            observer.update();
        }
    }
}
