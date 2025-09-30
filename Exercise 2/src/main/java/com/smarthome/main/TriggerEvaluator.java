package com.smarthome.main;

import com.smarthome.devices.Device;
import com.smarthome.devices.Thermostat;
import com.smarthome.proxy.DeviceProxy;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Map;

public class TriggerEvaluator {
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private final SmartHomeHub hub;

    public TriggerEvaluator(SmartHomeHub hub) {
        this.hub = hub;
    }

    public void startEvaluating() {
        executor.scheduleAtFixedRate(this::evaluateTriggers, 0, 1, TimeUnit.MINUTES);
    }

    private void evaluateTriggers() {
        List<Trigger> triggers = hub.getTriggers();
        Map<Integer, Device> devices = hub.getDevices();

        for (Trigger trigger : triggers) {
            if (evaluateCondition(trigger.getCondition(), devices)) {
                executeAction(trigger.getAction());
            }
        }
    }

    private boolean evaluateCondition(String condition, Map<Integer, Device> devices) {
        // Simple parser for conditions like "temperature > 75"
        // Supports only temperature and device status for now
        try {
            String[] parts = condition.split(" ");
            if (parts.length != 3) return false;

            String left = parts[0].toLowerCase();
            String operator = parts[1];
            String right = parts[2];

            if (left.equals("temperature")) {
                for (Device device : devices.values()) {
                    if (device.getType().equals("thermostat")) {
                        int temp = ((DeviceProxy) device).getTemperature();
                        int value = Integer.parseInt(right);
                        switch (operator) {
                            case ">":
                                if (temp > value) return true;
                                break;
                            case "<":
                                if (temp < value) return true;
                                break;
                            case "==":
                                if (temp == value) return true;
                                break;
                        }
                    }
                }
            } else if (left.startsWith("device")) {
                // e.g. device1 == on
                int deviceId = Integer.parseInt(left.substring(6));
                Device device = devices.get(deviceId);
                if (device == null) return false;
                boolean status = device.getStatus();
                boolean expected;
                String rightLower = right.toLowerCase();
                if (device.getType().equals("door")) {
                    expected = rightLower.equals("locked") || rightLower.equals("true");
                } else {
                    expected = rightLower.equals("on") || rightLower.equals("true");
                }
                switch (operator) {
                    case "==":
                        return status == expected;
                    case "!=":
                        return status != expected;
                    default:
                        return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error evaluating condition: " + condition);
        }
        return false;
    }

    private void executeAction(String action) {
        // Simple parser for actions like "turnOff 1"
        try {
            String[] parts = action.split(" ");
            if (parts.length != 2) return;

            String command = parts[0].toLowerCase();
            int deviceId = Integer.parseInt(parts[1]);

            switch (command) {
                case "turnon":
                    hub.turnOn(deviceId);
                    break;
                case "turnoff":
                    hub.turnOff(deviceId);
                    break;
                default:
                    System.out.println("Unknown action: " + action);
            }
        } catch (Exception e) {
            System.out.println("Error executing action: " + action);
        }
    }

    public void stop() {
        executor.shutdown();
    }
}
