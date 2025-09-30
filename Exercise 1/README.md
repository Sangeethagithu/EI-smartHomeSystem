
# Exercise 1 – Design Pattern  (Java, Maven)

Each pattern is a single-file demo with its own `main()` so reviewers can run them quickly.

## How to run

mvn -q clean package

# Observer – YouTube channel/subscribers
mvn -q exec:java -Dexec.mainClass=com.ei.patterns.behavioral.observer.youtube.Demo

# Strategy – Payments (UPI/CreditCard/DebitCard)
mvn -q exec:java -Dexec.mainClass=com.ei.patterns.behavioral.strategy.payment.Demo

# Singleton – Database connection (mock)
mvn -q exec:java -Dexec.mainClass=com.ei.patterns.creational.singleton.db.Demo

# Factory – Food ordering
mvn -q exec:java -Dexec.mainClass=com.ei.patterns.creational.factory.food.Demo

# Adapter – Smart AC via Wi-Fi / Bluetooth
mvn -q exec:java -Dexec.mainClass=com.ei.patterns.structural.adapter.ac.Demo

# Proxy – Video service (Netflix-like)
mvn -q exec:java -Dexec.mainClass=com.ei.patterns.structural.proxy.video.Demo
```
