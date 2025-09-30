package com.smarthome.devices;

public class Thermostat extends Device {
    private final int temperature;

    public Thermostat(int id, int temperature) {
        super(id, "thermostat", true);
        this.temperature = temperature;
    }

    @Override
    public String getStatusDescription() {
        return "Thermostat is set to " + temperature + " degrees.";
    }
}
