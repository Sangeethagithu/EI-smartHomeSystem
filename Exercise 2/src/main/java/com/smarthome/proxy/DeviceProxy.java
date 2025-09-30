package com.smarthome.proxy;

import com.smarthome.devices.Device;
import com.smarthome.devices.DoorLock;
import com.smarthome.devices.Thermostat;

public class DeviceProxy extends Device {
    public enum Role { ADMIN, GUEST }
    private Role role = Role.ADMIN;
    private final Device device;

    public DeviceProxy(Device device) {
        super(device.getId(), device.getType(), device.getStatus());
        this.device = device;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public void turnOn() {
        if (device.getType().equals("door") && role == Role.GUEST) {
            System.out.println("Access denied: GUEST cannot operate doors.");
            return;
        }
        device.turnOn();
    }

    @Override
    public void turnOff() {
        if (device.getType().equals("door") && role == Role.GUEST) {
            System.out.println("Access denied: GUEST cannot operate doors.");
            return;
        }
        device.turnOff();
    }

    @Override
    public String getStatusDescription() {
        return device.getStatusDescription();
    }

    public int getTemperature() {
        if (device instanceof Thermostat) {
            return ((Thermostat) device).getTemperature();
        }
        throw new UnsupportedOperationException("Not a thermostat");
    }

    public void setTemperature(int temperature) {
        if (device instanceof Thermostat) {
            ((Thermostat) device).setTemperature(temperature);
        } else {
            throw new UnsupportedOperationException("Not a thermostat");
        }
    }

    public String execute(String cmd, String... args) {
        if (device.getType().equalsIgnoreCase("door") && (cmd.equalsIgnoreCase("lock") || cmd.equalsIgnoreCase("unlock"))) {
            if (role != Role.ADMIN) {
                return "Access denied: need ADMIN role";
            }
        }
        switch (cmd.toLowerCase()) {
            case "turnon":
                turnOn();
                return "OK";
            case "turnoff":
                turnOff();
                return "OK";
            case "lock":
                if (device instanceof DoorLock) {
                    ((DoorLock) device).turnOn(); // lock means status true
                    return "OK";
                }
                return "Unknown command for Door";
            case "unlock":
                if (device instanceof DoorLock) {
                    ((DoorLock) device).turnOff(); // unlock means status false
                    return "OK";
                }
                return "Unknown command for Door";
            case "settemp":
                if (device instanceof Thermostat) {
                    if (args.length > 0) {
                        try {
                            int temp = Integer.parseInt(args[0]);
                            ((Thermostat) device).setTemperature(temp);
                            return "OK";
                        } catch (NumberFormatException e) {
                            return "Invalid temperature value";
                        }
                    }
                    return "Missing temperature argument";
                }
                return "Unknown command for Thermostat";
            default:
                return "Unknown command";
        }
    }
}
