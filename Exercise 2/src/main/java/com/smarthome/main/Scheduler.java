package com.smarthome.main;

import java.time.LocalTime;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private final SmartHomeHub hub;

    public Scheduler(SmartHomeHub hub) {
        this.hub = hub;
    }

    public void scheduleAll(List<Schedule> schedules) {
        for (Schedule schedule : schedules) {
            scheduleTask(schedule);
        }
    }

    private void scheduleTask(Schedule schedule) {
        LocalTime now = LocalTime.now();
        LocalTime scheduleTime = schedule.getTime();
        long delay = Duration.between(now, scheduleTime).toMillis();
        if (delay < 0) {
            delay += TimeUnit.DAYS.toMillis(1); // Schedule for next day
        }

        Runnable task = () -> {
            executeCommand(schedule.getDeviceId(), schedule.getCommand());
            // Reschedule for next day
            executor.schedule(() -> scheduleTask(schedule), TimeUnit.DAYS.toMillis(1), TimeUnit.MILLISECONDS);
        };

        executor.schedule(task, delay, TimeUnit.MILLISECONDS);
    }

    private void executeCommand(int deviceId, String command) {
        switch (command.toLowerCase()) {
            case "turnon":
                hub.turnOn(deviceId);
                break;
            case "turnoff":
                hub.turnOff(deviceId);
                break;
            default:
                System.out.println("Unknown command: " + command);
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
