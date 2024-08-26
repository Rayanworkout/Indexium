package com.github.rayanworkout.api.schema;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.rayanworkout.dto.RawDocument;
import com.github.rayanworkout.dto.Schema;
import com.github.rayanworkout.helpers.StringMethods;

public class SchemaValidator {

    public static boolean validate(RawDocument document, Schema schema) {
        Map<String, Object> documentData = document.getData();
        Map<String, String> schemaFields = schema.getFields();

        if (documentData == null || schemaFields == null) {
            return false;
        }

        Map<String, Object> lowercaseDocData = documentData.entrySet()
                .stream()
                .map(e -> new AbstractMap.SimpleEntry<>(
                        e.getKey().trim().toLowerCase(),
                        e.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return schemaFields.entrySet().stream()
                .allMatch(field -> {
                    String fieldName = field.getKey().trim();
                    String expectedType = field.getValue();
                    
                    // If the field sent is not in the schema of if there is an additional field
                    if (!lowercaseDocData.keySet().contains(fieldName)
                            || lowercaseDocData.keySet().size() != schemaFields.keySet().size()) {
                        return false;
                    }

                    Object value = lowercaseDocData.get(fieldName);

                    return validateValuesType(value, expectedType);
                });
    }

    private static boolean validateValuesType(Object value, String expectedType) {
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
