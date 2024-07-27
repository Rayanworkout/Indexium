package com.example.api.indexer;

import java.util.Map;

import com.example.api.dto.RawDocument;
import com.example.api.dto.Schema;

public class SchemaValidator {

    public static boolean validate(RawDocument document, Schema schema) {
        Map<String, Object> data = document.getData();
        Map<String, String> fields = schema.getFields();

        if (data == null || fields == null) {
            return false;
        }

        for (Map.Entry<String, String> field : fields.entrySet()) {
            String fieldName = field.getKey();
            String expectedType = field.getValue();

            if (!data.containsKey(fieldName)) {
                return false;
            }

            Object value = data.get(fieldName);
            if (!validateType(value, expectedType)) {
                return false;
            }
        }

        return true;
    }

    private static boolean validateType(Object value, String expectedType) {
        switch (expectedType) {
            case "String":
                return value instanceof String;
            case "Integer":
                return value instanceof Integer;
            case "Boolean":
                return value instanceof Boolean;
            default:
                return false;
        }
    }
}

