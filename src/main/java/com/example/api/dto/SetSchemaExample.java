package com.example.api.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SetSchemaExample {
    private Map<String, String> fields;

    public SetSchemaExample(Map<String, String> fields) {
        this.fields = fields;
    }

    // Static method to get the example schema
    public static SetSchemaExample getExampleSchema() {
        Map<String, String> fields = new HashMap<>();
        fields.put("title", "String");
        fields.put("content", "String");
        fields.put("author", "String");
        fields.put("publishDate", "Date");
        fields.put("views", "Integer");
        fields.put("isPublished", "Boolean");
        fields.put("rating", "Double");

        return new SetSchemaExample(Collections.unmodifiableMap(fields));
    }

    public Map<String, String> getFields() {
        return fields;
    }
}
