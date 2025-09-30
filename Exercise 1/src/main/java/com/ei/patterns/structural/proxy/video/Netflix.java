
package com.ei.patterns.structural.proxy.video;

// Main.java
// Example of Adapter Pattern: Controlling Air Conditioner via WiFi & Bluetooth

// Step 1: Common interface
interface DeviceInterface {
    void turnOn();
    void turnOff();
}

// Step 2: Existing WiFi-based AC
class WiFiAC {
    public void powerOnWiFi() {
        System.out.println("Air Conditioner turned ON via WiFi");
    }
    public void powerOffWiFi() {
        System.out.println("Air Conditioner turned OFF via WiFi");
    }
}

// Step 3: Existing Bluetooth-based AC
class BluetoothAC {
    public void startBluetooth() {
        System.out.println("Air Conditioner turned ON via Bluetooth");
    }
    public void stopBluetooth() {
        System.out.println("Air Conditioner turned OFF via Bluetooth");
    }
}

// Step 4: WiFi Adapter
class WiFiAdapter implements DeviceInterface {
    private WiFiAC wifiAC;
    public WiFiAdapter(WiFiAC wifiAC) {
        this.wifiAC = wifiAC;
    }
    @Override
    public void turnOn() {
        wifiAC.powerOnWiFi();
    }
    @Override
    public void turnOff() {
        wifiAC.powerOffWiFi();
    }
}

// Step 5: Bluetooth Adapter
class BluetoothAdapter implements DeviceInterface {
    private BluetoothAC bluetoothAC;
    public BluetoothAdapter(BluetoothAC bluetoothAC) {
        this.bluetoothAC = bluetoothAC;
    }
    @Override
    public void turnOn() {
        bluetoothAC.startBluetooth();
    }
    @Override
    public void turnOff() {
        bluetoothAC.stopBluetooth();
    }
}

// Step 6: Main class (Demo)
public class Netflix {
    public static void main(String[] args) {
        // Create actual devices
        WiFiAC wifiAC = new WiFiAC();
        BluetoothAC bluetoothAC = new BluetoothAC();

        // Use adapters
        DeviceInterface wifiAdapter = new WiFiAdapter(wifiAC);
        DeviceInterface bluetoothAdapter = new BluetoothAdapter(bluetoothAC);

        // Control ACs using same interface
        System.out.println("=== Controlling WiFi AC ===");
        wifiAdapter.turnOn();
        wifiAdapter.turnOff();

        System.out.println("\n=== Controlling Bluetooth AC ===");
        bluetoothAdapter.turnOn();
        bluetoothAdapter.turnOff();
    }
}







































































































