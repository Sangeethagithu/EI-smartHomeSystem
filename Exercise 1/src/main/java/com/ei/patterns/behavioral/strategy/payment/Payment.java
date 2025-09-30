
package com.ei.patterns.behavioral.strategy.payment;

// ---------------- Strategy Interface ----------------
interface PaymentStrategy {
    void processPayment(); // Abstract method for processing payments
}

// ---------------- Concrete Strategies ----------------
class CreditCardPayment implements PaymentStrategy {
    @Override
    public void processPayment() {
        System.out.println("Processing credit card payment...");
    }
}

class PayPalPayment implements PaymentStrategy {
    @Override
    public void processPayment() {
        System.out.println("Processing PayPal payment...");
    }
}

class CryptoPayment implements PaymentStrategy {
    @Override
    public void processPayment() {
        System.out.println("Processing crypto payment...");
    }
}

class StripePayment implements PaymentStrategy {
    @Override
    public void processPayment() {
        System.out.println("Processing Stripe payment...");
    }
}

// ---------------- Context Class ----------------
class PaymentProcessor {
    private PaymentStrategy paymentStrategy; // Reference to current payment strategy

    // Constructor to set initial strategy
    public PaymentProcessor(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // Process payment using the chosen strategy
    public void processPayment() {
        paymentStrategy.processPayment();
    }

    // Dynamically change strategy at runtime
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
}

// ---------------- Demo Class ----------------
public class Payment {
    public static void main(String[] args) {
        // Create strategy instances
        PaymentStrategy creditCard = new CreditCardPayment();
        PaymentStrategy payPal = new PayPalPayment();
        PaymentStrategy crypto = new CryptoPayment();
        PaymentStrategy stripe = new StripePayment();

        // Use Strategy Pattern in action
        PaymentProcessor processor = new PaymentProcessor(creditCard);
        processor.processPayment(); // Processing credit card payment...

        processor.setPaymentStrategy(payPal);
        processor.processPayment(); // Processing PayPal payment...

        processor.setPaymentStrategy(crypto);
        processor.processPayment(); // Processing crypto payment...

        processor.setPaymentStrategy(stripe);
        processor.processPayment(); // Processing Stripe payment...
    }
}















