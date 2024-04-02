package com.springapi.api.common.utils;

public class Utils {

    public static void validateNotEmpty(String value, String fieldName) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
    }
}
