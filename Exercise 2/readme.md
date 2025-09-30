# Smart Home System – Design Patterns 
This project simulates a Smart Home System where users can control devices like Lights, Thermostats, and Door Locks through a central hub.  
The focus is to demonstrate Object-Oriented Programming and the application of Design Patterns commonly expected in real-world software development.

Creational Pattern – Factory for creating devices  
Structural Pattern – Proxy for secure access control (role based access control)
Behavioral Pattern – Observer for device state updates  
---
## Tech Stack
- Java 17+
- Maven (build tool)
- Console-based (menu-driven CLI)
---
## Setup & Run
1. Clone or download this repository.
2. Open a terminal inside the project folder.
3. Build the project:mvn clean package

---

## Feature

- *Device Management (Factory Pattern)**  
  - Devices like **Light**, **Thermostat**, and **Door Lock** are created dynamically through a central `DeviceFactory`.  
  - This ensures the system is **easily extensible** — new device types can be added without modifying existing logic.

- *Secure Access Control (Proxy Pattern)**  
  - Sensitive operations (e.g., **unlocking doors**) are controlled via a `SecureDeviceProxy`.  
  - Only **Admin** role can execute high-privilege commands, while **Guest** role is restricted.  
  - This simulates **real-world authentication & authorization** in IoT systems.

- *Event Notifications (Observer Pattern)**  
  - Every device state change automatically notifies observers.  
  - Example: Turning on a light logs:  
    ```
    [Observer] Light#1 : ON
    ```  
  - This decouples device logic from monitoring and highlights **event-driven design**.

- *Automation with Triggers**  
  - Users can define conditional rules for automation.  
  - Example: *If thermostat temperature > 25 → turn off Light 1*.  
  - Devices can **react to each other** without manual input.

- *Smart Scheduling**  
  - Devices can be scheduled to run at specific times.  
  - Supports both **manual simulation** (`runNow`) and **real-time execution** using Java’s `ScheduledExecutorService`.  
  - Example: *Turn on Light 1 at 06:00 automatically*.

- *Central Hub (Menu-Driven CLI)**  
  - A single **Hub** manages all devices, schedules, and triggers.  
  - User-friendly **menu interface** makes it simple to add/remove devices, set schedules, or check status.  
  - Acts like a **smart home controller** in a simplified simulation.

- *Extensible & Maintainable (SOLID Principles)**  
  - *Single Responsibility** → Each class (Hub, DeviceFactory, Proxy, Observer) has a focused role.  
  - *Open-Closed Principle** → New device types can be added without changing existing code.  
  - *Polymorphism** → All devices share a base `SmartDevice` interface, making the system consistent and scalable.

---
Example:
These are the commands:
![image alt](https://github.com/Sangeethagithu/EI-smartHomeSystem/blob/ffc5e9b8a7d51d2c4fbfd41d9e7417c58ebd20f5/Screenshot%202025-09-30%20212245.png)

Proxy Pattern:
This system has role-based-access control.It has 2 roles: Guest and admin.
Only admin can control the devices ,Guest cannot change the state of the devices.

![image alt](https://github.com/Sangeethagithu/EI-smartHomeSystem/blob/18632a76e843a98577075c99be5a3dfc795aeafa/Screenshot%202025-09-30%20211544.png)

![image alt](https://github.com/Sangeethagithu/EI-smartHomeSystem/blob/5e7bbadc05a57af655cafe21efddb7c40a492d6e/Screenshot%202025-09-30%20211624.png)

![image alt](https://github.com/Sangeethagithu/EI-smartHomeSystem/blob/6fa6a77165c295f7bcf11979ce17b21f26f14e17/Screenshot%202025-09-30%20211701.png)

Trigger:
Add devices:
 ![image alt](https://github.com/Sangeethagithu/EI-smartHomeSystem/blob/99da8b91fbf129fbee94d05e93cf177094d69e1a/Screenshot%202025-09-30%20220222.png)
  ![image alt](https://github.com/Sangeethagithu/EI-smartHomeSystem/blob/947608d9ee1146c953992410e88ac8cafe86faff/Screenshot%202025-09-30%20220245.png)
 
Create  and show trigger:
 ![image alt](https://github.com/Sangeethagithu/EI-smartHomeSystem/blob/91290b6ba4882f18e10ea20209fd39e973c44fee/Screenshot%202025-09-30%20220311.png)
  ![image alt](https://github.com/Sangeethagithu/EI-smartHomeSystem/blob/9be83b420b4b6298a98c8d1fce85f349890c7e90/Screenshot%202025-09-30%20220328.png)
 
Effect of trigger:
 ![image alt]()




