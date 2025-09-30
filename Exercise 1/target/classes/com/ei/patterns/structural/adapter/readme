# Adapter Pattern – Smart AC Example

## Overview
The **Adapter Pattern** is a structural design pattern that allows incompatible interfaces to work 
together.  
It acts as a bridge between two systems by converting one interface into another that the client 
expects.

---

## Problem
In real systems:
- Different devices may expose **different interfaces** (e.g., WiFi AC vs Bluetooth AC).  
- A client may want to control all ACs using a **common interface** (`turnOn()`, `turnOff()`).  
- Without an adapter, you end up writing duplicate code or tightly coupling the client with each 
implementation.

---
## Solution
The Adapter pattern solves this problem by:
- Defining a **target interface** (`SmartAC`) that the client will use.  
- Creating **adapters** (`ACAdapter`) to wrap existing classes (`WifiAC`, `BluetoothAC`).  
- Translating client calls (`turnOn()`, `turnOff()`) into appropriate method calls on the adaptee.  

This allows the client to interact with different implementations through a unified interface.

---