package com.smarthome.main;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Schedule {
    private final int deviceId;
    private final LocalTime time;
    private final String command;

    public Schedule(int deviceId, String time, String command) {
        this.deviceId = deviceId;
        this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        this.command = command;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return "{device: " + deviceId + ", time: \"" + time + "\", command: \"" + command + "\"}";
    }
}
