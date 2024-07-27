package com.example.api.schema;

import org.springframework.stereotype.Service;

import com.example.api.dto.Schema;

/**
 * Shared service between the IndexerController and SchemaController;
 * It allows me to access and modify a single instance of schema
 * It is a kind of dependency injection
 */
@Service
public class SchemaService {
    private Schema currentSchema;

    public Schema getCurrentSchema() {
        return currentSchema;
    }

    public void setCurrentSchema(Schema schema) {
        this.currentSchema = schema;
    }
}
