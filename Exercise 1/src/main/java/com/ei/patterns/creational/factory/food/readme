

# Factory Pattern – Food Order Example

## Overview
The **Factory Pattern** is a creational design pattern that provides an interface for creating objects,  
but allows subclasses or a factory class to decide which class to instantiate.  
It centralizes object creation and makes the system more flexible to changes.

---

## Problem
In a food ordering system:
- If you use `new Pizza()`, `new Burger()`, `new Sandwich()` directly in the client,  
  the code becomes **tightly coupled** to concrete classes.  
- Adding a new food item requires modifying client code everywhere.  
- This violates the **Open-Closed Principle** (OCP).

---

## Solution
The Factory pattern solves this problem by:
- Defining a **common interface** (`Food`) for all products.  
- Implementing concrete classes (`Pizza`, `Burger`, `Sandwich`) for each product.  
- Using a **factory class** (`FoodFactory`) to handle object creation.  
- Keeping client code independent of product classes.

---
