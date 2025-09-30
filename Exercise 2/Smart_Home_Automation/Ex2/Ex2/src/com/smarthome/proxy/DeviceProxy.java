package com.smarthome.proxy;

import com.smarthome.devices.Device;

public class DeviceProxy extends Device {
    private final Device realDevice;

    public DeviceProxy(Device realDevice) {
        super(realDevice.getId(), realDevice.getType(), realDevice.getStatus());
        this.realDevice = realDevice;
    }

    @Override
    public void turnOn() {
        realDevice.turnOn();
    }

    @Override
    public void turnOff() {
        realDevice.turnOff();
    }

    @Override
    public String getStatusDescription() {
        return realDevice.getStatusDescription();
    }
}
