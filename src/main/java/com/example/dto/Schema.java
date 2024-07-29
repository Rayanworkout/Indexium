package com.example.dto;

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

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
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

        Set<String> capitalizedTypes = new HashSet<>();
        for (String type : fields.values()) {
            String capitalizedType = StringMethods.capitalize(type);
            capitalizedTypes.add(capitalizedType);
        }

        // Validate that all capitalized types are acceptable
        return capitalizedTypes.stream().allMatch(ACCEPTABLE_TYPES::contains);
    }
}
