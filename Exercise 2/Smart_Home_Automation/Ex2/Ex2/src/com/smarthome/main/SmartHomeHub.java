package com.smarthome.main;

import com.smarthome.devices.Device;
import com.smarthome.factory.DeviceFactory;
import com.smarthome.proxy.DeviceProxy;
import com.smarthome.observer.Subject;
import java.util.*;

public class SmartHomeHub extends Subject {
    private final Map<Integer, Device> devices = new HashMap<>();
    private final List<Schedule> schedules = new ArrayList<>();
    private final List<Trigger> triggers = new ArrayList<>();

    public void addDevice(int id, String type) {
        if (devices.containsKey(id)) {
            System.out.println("Device with ID " + id + " already exists.");
            return;
        }

        Device device = null;
        Scanner scanner = new Scanner(System.in);

        switch (type.toLowerCase()) {
            case "light":
                System.out.println("Enter the initial status for the light (on/off):");
                String lightStatus = scanner.nextLine().trim().toLowerCase();
                boolean isLightOn = lightStatus.equals("on");
                device = DeviceFactory.createDevice(type, id, isLightOn);
                break;
            case "thermostat":
                System.out.println("Enter the initial temperature for the thermostat:");
                int temperature = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                device = DeviceFactory.createDevice(type, id, temperature);
                break;
            case "door":
                System.out.println("Enter the initial status for the door (locked/unlocked):");
                String doorStatus = scanner.nextLine().trim().toLowerCase();
                boolean isDoorLocked = doorStatus.equals("locked");
                device = DeviceFactory.createDevice(type, id, isDoorLocked);
                break;
            default:
                System.out.println("Invalid device type.");
                return;
        }

        if (device != null) {
            devices.put(id, new DeviceProxy(device));
            notifyObservers();
            System.out.println("Device added successfully.");
        }
    }

    public void removeDevice(int id) {
        if (devices.remove(id) != null) {
            notifyObservers();
            System.out.println("Device removed successfully.");
        } else {
            System.out.println("No device found with ID " + id + ".");
        }
    }

    public void turnOn(int id) {
        Device device = devices.get(id);
        if (device != null) {
            device.turnOn();
            notifyObservers();
            System.out.println("Device " + id + " turned on.");
        } else {
            System.out.println("No device found with ID " + id + ".");
        }
    }

    public void turnOff(int id) {
        Device device = devices.get(id);
        if (device != null) {
            device.turnOff();
            notifyObservers();
            System.out.println("Device " + id + " turned off.");
        } else {
            System.out.println("No device found with ID " + id + ".");
        }
    }

    public void setSchedule(int deviceId, String time, String command) {
        if (devices.containsKey(deviceId)) {
            schedules.add(new Schedule(deviceId, time, command));
            System.out.println("Schedule added.");
        } else {
            System.out.println("No device found with ID " + deviceId + ".");
        }
    }

    public void addTrigger(String condition, String action) {
        triggers.add(new Trigger(condition, action));
        System.out.println("Trigger added.");
    }

    public void showStatus() {
        if (devices.isEmpty()) {
            System.out.println("No devices available.");
            return;
        }

        devices.values().forEach(device -> {
            System.out.println(device.getStatusDescription());
        });
    }

    public void showSchedules() {
        if (schedules.isEmpty()) {
            System.out.println("No schedules set.");
            return;
        }

        schedules.forEach(System.out::println);
    }

    public void showTriggers() {
        if (triggers.isEmpty()) {
            System.out.println("No triggers set.");
            return;
        }

        triggers.forEach(System.out::println);
    }

    public Map<Integer, Device> getDevices() {
        return devices;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public List<Trigger> getTriggers() {
        return triggers;
    }
}
