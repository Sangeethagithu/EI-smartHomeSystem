package com.smarthome.devices;

public abstract class Device {
    private final int id;
    private final String type;
    private boolean status;

    public Device(int id, String type, boolean status) {
        this.id = id;
        this.type = type;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public boolean getStatus() {
        return status;
    }

    public void turnOn() {
        status = true;
    }

    public void turnOff() {
        status = false;
    }

    public abstract String getStatusDescription();
}
