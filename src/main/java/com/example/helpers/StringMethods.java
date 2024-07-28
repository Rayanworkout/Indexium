package com.example.helpers;

public class StringMethods {
    public static String capitalize(String type) {
        if (type == null || type.isEmpty()) {
            return type;
        }
        return type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
    }
}
