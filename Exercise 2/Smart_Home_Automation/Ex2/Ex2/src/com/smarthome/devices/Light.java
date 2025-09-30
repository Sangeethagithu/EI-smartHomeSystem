package com.smarthome.devices;

public class Light extends Device {
    public Light(int id, boolean status) {
        super(id, "light", status);
    }

    @Override
    public String getStatusDescription() {
        return "Light " + getId() + " is " + (getStatus() ? "On" : "Off") + ".";
    }
}
