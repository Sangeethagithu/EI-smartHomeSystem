package com.smarthome.main;

public class Trigger {
    private final String condition;
    private final String action;

    public Trigger(String condition, String action) {
        this.condition = condition;
        this.action = action;
    }

    public String getCondition() {
        return condition;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return "{condition: \"" + condition + "\", action: \"" + action + "\"}";
    }
}
