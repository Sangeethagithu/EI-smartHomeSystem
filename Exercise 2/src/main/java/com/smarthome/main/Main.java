package com.smarthome.main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SmartHomeHub hub = new SmartHomeHub();
        hub.startRealTimeFeatures();
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("\nSmart Home Hub Menu:");
            System.out.println("1. Add Device");
            System.out.println("2. Remove Device");
            System.out.println("3. Turn On Device");
            System.out.println("4. Turn Off Device");
            System.out.println("5. Set Schedule");
            System.out.println("6. Add Trigger");
            System.out.println("7. Show Status");
            System.out.println("8. Show Schedules");
            System.out.println("9. Show Triggers");
            System.out.println("10. Run Schedule Now");
            System.out.println("11. Set Thermostat Temperature");
            System.out.println("12. Set Device Role");
            System.out.println("13. Exit");

            System.out.print("Enter choice: ");
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    System.out.print("Enter device ID: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter device type (light/thermostat/door): ");
                        String type = scanner.nextLine();
                        hub.addDevice(id, type);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID. Please enter a number.");
                    }
                    break;
                case "2":
                    System.out.print("Enter device ID to remove: ");
                    try {
                        int removeId = Integer.parseInt(scanner.nextLine());
                        hub.removeDevice(removeId);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID. Please enter a number.");
                    }
                    break;
                case "3":
                    System.out.print("Enter device ID to turn on: ");
                    try {
                        int turnOnId = Integer.parseInt(scanner.nextLine());
                        hub.turnOn(turnOnId);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID. Please enter a number.");
                    }
                    break;
                case "4":
                    System.out.print("Enter device ID to turn off: ");
                    try {
                        int turnOffId = Integer.parseInt(scanner.nextLine());
                        hub.turnOff(turnOffId);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID. Please enter a number.");
                    }
                    break;
                case "5":
                    System.out.print("Enter device ID for schedule: ");
                    try {
                        int scheduleId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter schedule time: ");
                        String time = scanner.nextLine();
                        System.out.print("Enter schedule command:(lock,unlock,turnon,turnoff ) ");
                        String commandText = scanner.nextLine();
                        hub.setSchedule(scheduleId, time, commandText);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID. Please enter a number.");
                    }
                    break;
                case "6":
                    System.out.print("Enter trigger condition:(eg temperature > 75) ");
                    String condition = scanner.nextLine();
                    System.out.print("Enter trigger action:(for eg turnOff(1)) ");
                    String action = scanner.nextLine();
                    hub.addTrigger(condition, action);
                    break;
                case "7":
                    hub.showStatus();
                    break;
                case "8":
                    hub.showSchedules();
                    break;
                case "9":
                    hub.showTriggers();
                    break;
                case "10":
                    System.out.print("Enter schedule index (0-based): ");
                    try {
                        int index = Integer.parseInt(scanner.nextLine());
                        hub.runNow(index);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid index.");
                    }
                    break;
                case "11":
                    System.out.print("Enter thermostat ID: ");
                    try {
                        int thermoId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter new temperature: ");
                        int temperature = Integer.parseInt(scanner.nextLine());
                        hub.setThermostatTemperature(thermoId, temperature);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter numbers.");
                    }
                    break;
                case "12":
                    System.out.print("Enter device ID: ");
                    try {
                        int roleId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter role (ADMIN/GUEST): ");
                        String role = scanner.nextLine();
                        hub.setDeviceRole(roleId, role);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID. Please enter a number.");
                    }
                    break;
                case "13":
                    System.out.println("Exiting...");
                    hub.shutdown();
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
