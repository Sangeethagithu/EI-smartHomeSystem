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
    private Scheduler scheduler;
    private TriggerEvaluator triggerEvaluator;

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
                try {
                    int temperature = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    device = DeviceFactory.createDevice(type, id, temperature);
                } catch (InputMismatchException e) {
                    System.out.println("Invalid temperature. Please enter a number.");
                    scanner.nextLine(); // Consume invalid input
                }
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
            boolean wasOn = device.getStatus();
            device.turnOn();
            if (device.getStatus() != wasOn) {
                notifyObservers();
                System.out.println("Device " + id + " turned on.");
            }
        } else {
            System.out.println("No device found with ID " + id + ".");
        }
    }

    public void turnOff(int id) {
        Device device = devices.get(id);
        if (device != null) {
            boolean wasOn = device.getStatus();
            device.turnOff();
            if (device.getStatus() != wasOn) {
                notifyObservers();
                System.out.println("Device " + id + " turned off.");
            }
        } else {
            System.out.println("No device found with ID " + id + ".");
        }
    }

    public void setSchedule(int deviceId, String time, String command) {
        if (!devices.containsKey(deviceId)) {
            System.out.println("No device found with ID " + deviceId + ".");
            return;
        }
        String cmdLower = command.toLowerCase();
        if (!cmdLower.equals("turnon") && !cmdLower.equals("turnoff") && !cmdLower.equals("lock") && !cmdLower.equals("unlock")) {
            System.out.println("Invalid command. Use 'turnon', 'turnoff', 'lock', or 'unlock'.");
            return;
        }
        try {
            schedules.add(new Schedule(deviceId, time, command));
            System.out.println("Schedule added.");
        } catch (Exception e) {
            System.out.println("Invalid time format. Use HH:mm.");
        }
    }

    public void addTrigger(String condition, String action) {
        if (!isValidCondition(condition)) {
            System.out.println("Invalid condition format. Use 'temperature > 75' or 'device1 == on'.");
            return;
        }
        if (!isValidAction(action)) {
            System.out.println("Invalid action format. Use 'turnon 1' or 'turnoff 1'.");
            return;
        }
        triggers.add(new Trigger(condition, action));
        System.out.println("Trigger added.");
    }

    private boolean isValidCondition(String condition) {
        return condition.matches("temperature\\s*(>|<|==)\\s*\\d+") ||
               condition.matches("device\\d+\\s*(==|!=)\\s*(on|off|true|false|locked|unlocked)");
    }

    private boolean isValidAction(String action) {
        return action.matches("(turnon|turnoff)\\s+\\d+");
    }

    public void showStatus() {
        if (devices.isEmpty()) {
            System.out.println("No devices available.");
            return;
        }

        devices.values().forEach(device -> {
            System.out.println("Device " + device.getId() + ": " + device.getStatusDescription());
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

    public void startRealTimeFeatures() {
        scheduler = new Scheduler(this);
        scheduler.scheduleAll(schedules);
        triggerEvaluator = new TriggerEvaluator(this);
        triggerEvaluator.startEvaluating();
    }

    public void setThermostatTemperature(int id, int temperature) {
        Device device = devices.get(id);
        if (device != null && device.getType().equals("thermostat")) {
            ((DeviceProxy) device).setTemperature(temperature);
            notifyObservers();
            System.out.println("Thermostat " + id + " temperature set to " + temperature + " degrees.");
        } else {
            System.out.println("No thermostat found with ID " + id + ".");
        }
    }

    public void runNow(int scheduleIndex) {
        if (scheduleIndex < 0 || scheduleIndex >= schedules.size()) {
            System.out.println("Invalid schedule index.");
            return;
        }
        Schedule schedule = schedules.get(scheduleIndex);
        switch (schedule.getCommand().toLowerCase()) {
            case "turnon":
                turnOn(schedule.getDeviceId());
                break;
            case "turnoff":
                turnOff(schedule.getDeviceId());
                break;
            case "lock":
                turnOn(schedule.getDeviceId()); // lock is turnOn for door
                break;
            case "unlock":
                turnOff(schedule.getDeviceId()); // unlock is turnOff for door
                break;
            default:
                System.out.println("Unknown command in schedule.");
        }
    }

    public void setDeviceRole(int id, String roleStr) {
        Device device = devices.get(id);
        if (device != null && device instanceof DeviceProxy) {
            try {
                DeviceProxy.Role role = DeviceProxy.Role.valueOf(roleStr.toUpperCase());
                ((DeviceProxy) device).setRole(role);
                System.out.println("Role for device " + id + " set to " + role + ".");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid role. Use ADMIN or GUEST.");
            }
        } else {
            System.out.println("No device found with ID " + id + ".");
        }
    }

    public void shutdown() {
        if (scheduler != null) scheduler.shutdown();
        if (triggerEvaluator != null) triggerEvaluator.stop();
    }

}
