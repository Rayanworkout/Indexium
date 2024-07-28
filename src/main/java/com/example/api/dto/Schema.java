package com.example.api.dto;

import java.util.Map;

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
}
