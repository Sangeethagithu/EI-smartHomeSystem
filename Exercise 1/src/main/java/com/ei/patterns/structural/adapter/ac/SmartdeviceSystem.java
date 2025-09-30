
package com.ei.patterns.structural.adapter.ac;



// ---------------- Target Interface ----------------
interface SmartDevice {
    void turnOn();
    void turnOff();
}

// ---------------- Adaptees (Existing Classes) ----------------
class AirConditioner {
    public void connectViaBluetooth() {
        System.out.println("Air Conditioner connected via Bluetooth.");
    }
    public void startCooling() {
        System.out.println("Air Conditioner is now cooling.");
    }
    public void stopCooling() {
        System.out.println("Air Conditioner stopped cooling.");
    }
    public void disconnectBluetooth() {
        System.out.println("Air Conditioner disconnected from Bluetooth.");
    }
}

class SmartLight {
    public void connectToWiFi() {
        System.out.println("Smart Light connected to Wi-Fi.");
    }
    public void switchOn() {
        System.out.println("Smart Light is now ON.");
    }
    public void switchOff() {
        System.out.println("Smart Light is now OFF.");
    }
    public void disconnectWiFi() {
        System.out.println("Smart Light disconnected from Wi-Fi.");
    }
}

class CoffeeMachine {
    public void initializeZigbeeConnection() {
        System.out.println("Coffee Machine connected via Zigbee.");
    }
    public void startBrewing() {
        System.out.println("Coffee Machine is now brewing coffee.");
    }
    public void stopBrewing() {
        System.out.println("Coffee Machine stopped brewing coffee.");
    }
    public void terminateZigbeeConnection() {
        System.out.println("Coffee Machine disconnected from Zigbee.");
    }
}

// ---------------- Adapters ----------------
class AirConditionerAdapter implements SmartDevice {
    private AirConditioner airConditioner;

    public AirConditionerAdapter(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Override
    public void turnOn() {
        airConditioner.connectViaBluetooth();
        airConditioner.startCooling();
    }

    @Override
    public void turnOff() {
        airConditioner.stopCooling();
        airConditioner.disconnectBluetooth();
    }
}

class SmartLightAdapter implements SmartDevice {
    private SmartLight smartLight;

    public SmartLightAdapter(SmartLight smartLight) {
        this.smartLight = smartLight;
    }

    @Override
    public void turnOn() {
        smartLight.connectToWiFi();
        smartLight.switchOn();
    }

    @Override
    public void turnOff() {
        smartLight.switchOff();
        smartLight.disconnectWiFi();
    }
}

class CoffeeMachineAdapter implements SmartDevice {
    private CoffeeMachine coffeeMachine;

    public CoffeeMachineAdapter(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    @Override
    public void turnOn() {
        coffeeMachine.initializeZigbeeConnection();
        coffeeMachine.startBrewing();
    }

    @Override
    public void turnOff() {
        coffeeMachine.stopBrewing();
        coffeeMachine.terminateZigbeeConnection();
    }
}

// ---------------- Client (Controller) ----------------
public class SmartdeviceSystem {
    public static void main(String[] args) {
        // Wrap devices with adapters
        SmartDevice airConditioner = new AirConditionerAdapter(new AirConditioner());
        SmartDevice smartLight = new SmartLightAdapter(new SmartLight());
        SmartDevice coffeeMachine = new CoffeeMachineAdapter(new CoffeeMachine());

        // Control devices via common SmartDevice interface
        airConditioner.turnOn();
        smartLight.turnOn();
        coffeeMachine.turnOn();

        airConditioner.turnOff();
        smartLight.turnOff();
        coffeeMachine.turnOff();
    }
}


















