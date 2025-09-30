
package com.ei.patterns.creational.factory.food;

interface FoodItem { String name(); int price(); } // paise

class Pizza implements FoodItem { public String name(){ return "Pizza"; } public int price(){ return 29900; } }
class Burger implements FoodItem { public String name(){ return "Burger"; } public int price(){ return 14900; } }
class Idli implements FoodItem { public String name(){ return "Idli"; } public int price(){ return 4000; } }

class FoodFactory {
    static FoodItem create(String type){
        return switch(type.toLowerCase()){
            case "pizza" -> new Pizza();
            case "burger" -> new Burger();
            case "idli" -> new Idli();
            default -> throw new IllegalArgumentException("Unknown item: "+type);
        };
    }
}

public class Food {
    public static void main(String[] args) {
        FoodItem f1 = FoodFactory.create("pizza");
        FoodItem f2 = FoodFactory.create("idli");
        System.out.println(f1.name()+" Rs "+f1.price()/100.0);
        System.out.println(f2.name()+" Rs "+f2.price()/100.0);
    }
}
