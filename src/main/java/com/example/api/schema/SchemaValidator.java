package com.example.api.schema;

import java.util.Map;

import com.example.dto.RawDocument;
import com.example.dto.Schema;
import com.example.helpers.StringMethods;

public class SchemaValidator {

    public static boolean validate(RawDocument document, Schema schema) {
        Map<String, Object> data = document.getData();
        Map<String, String> schemaFields = schema.getFields();

        if (data == null || schemaFields == null) {
            return false;
        }

        for (Map.Entry<String, String> field : schemaFields.entrySet()) {
            String fieldName = field.getKey().trim().toLowerCase();
            String expectedType = field.getValue();

            if (!data.keySet().contains(fieldName)) {
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
        switch (StringMethods.capitalize(expectedType)) {
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
