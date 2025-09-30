package com.smarthome.factory;

import com.smarthome.devices.*;

public class DeviceFactory {
    public static Device createDevice(String type, int id, boolean status) {
        switch (type.toLowerCase()) {
            case "light":
                return new Light(id, status);
            case "door":
                return new DoorLock(id, status);
            default:
                throw new IllegalArgumentException("Invalid device type.");
        }
    }

    public static Device createDevice(String type, int id, int temperature) {
        if ("thermostat".equalsIgnoreCase(type)) {
            return new Thermostat(id, temperature);
        }
        throw new IllegalArgumentException("Invalid device type.");
    }
}
