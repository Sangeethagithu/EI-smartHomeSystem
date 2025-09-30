package com.smarthome.devices;

public class DoorLock extends Device {
    public DoorLock(int id, boolean status) {
        super(id, "door", status);
    }

    @Override
    public String getStatusDescription() {
        return "Door is " + (getStatus() ? "Locked" : "Unlocked") + ".";
    }
}
