package com.example.sustainability.api.data;



public enum ActionType {
    LIGHTS("LIGHTS"),
    COMPUTER("COMPUTER"),
    WINDOW("WINDOW");


    private final String value;

    private ActionType(String value) {
        this.value = value.toUpperCase();
    }

    public String getValue() {
        return value;
    }
}

