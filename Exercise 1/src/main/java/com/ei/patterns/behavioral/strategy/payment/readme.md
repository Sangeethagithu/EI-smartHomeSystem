# Strategy Pattern – Payment Example

## Overview
The **Strategy Pattern** is a behavioral design pattern that enables selecting an algorithm's behavior at runtime.  
Instead of implementing a single algorithm directly, the pattern allows you to choose from a family of algorithms, encapsulate each one, and make them interchangeable.

---

## Problem
In payment systems (or similar contexts):
- You may need multiple ways to process a payment (UPI, Credit Card, Debit Card).  
- Without Strategy, this often leads to **long if-else or switch statements** scattered across the code.  
- Adding a new payment method means modifying existing code, violating the **Open-Closed Principle**.

---

## Solution
The Strategy pattern solves this problem by:
- Defining a **common interface** (`PaymentStrategy`) for all payment methods.  
- Implementing each payment option (UPI, Credit Card, Debit Card) as separate **strategies**.  
- Allowing the **context class** (`PaymentService`) to switch strategies at runtime.  

This keeps the code flexible, clean, and open to extension.

---