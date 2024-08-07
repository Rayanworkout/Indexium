package com.github.rayanworkout.dto;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

        return fields == null ? null : fields;
    }

    public static final Set<String> ACCEPTABLE_TYPES = new HashSet<>();

    static {
        ACCEPTABLE_TYPES.add("String");
        ACCEPTABLE_TYPES.add("Integer");
        ACCEPTABLE_TYPES.add("Boolean");
        ACCEPTABLE_TYPES.add("Double");
    }

}
