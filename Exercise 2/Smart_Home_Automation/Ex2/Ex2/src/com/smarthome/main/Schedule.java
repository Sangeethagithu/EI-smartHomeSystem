package com.smarthome.main;

public class Schedule {
    private final int deviceId;
    private final String time;
    private final String command;

    public Schedule(int deviceId, String time, String command) {
        this.deviceId = deviceId;
        this.time = time;
        this.command = command;
    }

    @Override
    public String toString() {
        return "{device: " + deviceId + ", time: \"" + time + "\", command: \"" + command + "\"}";
    }
}
