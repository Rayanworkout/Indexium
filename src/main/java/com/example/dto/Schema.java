package com.example.dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.example.helpers.StringMethods;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class Schema {

    @JsonCreator
    public Schema(@JsonProperty("fields") Map<String, String> fields) {
        this.fields = fields;
    }

    @NotEmpty(message = "Fields cannot be empty")
    private Map<String, String> fields;

    public Map<String, String> getFields() {

        return fields;
    }

    public static final Set<String> ACCEPTABLE_TYPES = new HashSet<>();

    static {
        ACCEPTABLE_TYPES.add("String");
        ACCEPTABLE_TYPES.add("Integer");
        ACCEPTABLE_TYPES.add("Boolean");
        ACCEPTABLE_TYPES.add("Double");
    }

    public boolean validateSchemaTypes() {
        if (fields == null || fields.isEmpty()) {
            return false;
        }

        // Validate that all capitalized types are acceptable
        return fields.values()
                .stream()
                .map(e -> StringMethods.capitalize(e))
                .allMatch(ACCEPTABLE_TYPES::contains);
    }

    /**
     * Returns a new Schema object with lowercase keys in the fields map.
     */
    public Schema schemaToLowercase() {
        if (fields == null) {
            return new Schema(new HashMap<>());
        }

        Map<String, String> lowerCaseFields = new HashMap<>();

        for (Map.Entry<String, String> entry : fields.entrySet()) {
            lowerCaseFields.put(entry.getKey().toLowerCase(), entry.getValue().toLowerCase());
        }

        return new Schema(lowerCaseFields);
    }
}
