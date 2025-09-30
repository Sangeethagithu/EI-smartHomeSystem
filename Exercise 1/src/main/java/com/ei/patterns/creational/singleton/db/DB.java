
package com.ei.patterns.creational.singleton.db;

public class DB {
    public static void main(String[] args) {
        Database d1 = Database.getInstance();
        Database d2 = Database.getInstance();
        System.out.println("Same instance? " + (d1 == d2));
        System.out.println(d1.query("SELECT * FROM users"));
    }
}

final class Database {
    private static final Database INSTANCE = new Database();
    private Database(){ /* imagine pool/config here */ }
    public static Database getInstance(){ return INSTANCE; }
    public String query(String sql){ return "Result for: " + sql; }
}
