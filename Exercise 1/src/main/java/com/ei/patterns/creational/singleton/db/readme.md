# Singleton Pattern – Database Connection Example

## Overview
The **Singleton Pattern** is a creational design pattern that ensures a class has only **one instance** throughout the application,  
and provides a global access point to that instance.

---

## Problem
In systems that use shared resources (like databases, configuration managers, loggers):
- Creating multiple instances wastes memory and resources.  
- It can cause **inconsistent states** if multiple objects represent the same resource.  
- Without Singleton, every `new DatabaseConnection()` creates a new object unnecessarily.

---

## Solution
The Singleton pattern solves this problem by:
- Making the **constructor private** to prevent direct instantiation.  
- Providing a **static method** (`getInstance()`) to return the single object.  
- Ensuring all parts of the application share the same instance.

---