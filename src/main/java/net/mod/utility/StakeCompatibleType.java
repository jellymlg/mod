package net.mod.utility;

import java.util.HashMap;

public enum StakeCompatibleType {
    TOMATO("tomato"),
    PEPPER("pepper");
    private final String type;
    private static final HashMap<String,StakeCompatibleType> list = new HashMap<String,StakeCompatibleType>(2);
    private StakeCompatibleType(String type) {
        this.type = type;
    }
    public static String asString(StakeCompatibleType entry) {
        return entry.type;
    }
    public static StakeCompatibleType getFromString(String type) {
        return list.get(type);
    }
    static {
        list.put(TOMATO.type, TOMATO);
        list.put(PEPPER.type, PEPPER);
    }
}