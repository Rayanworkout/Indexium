package com.example.api.schema;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.api.exception.SchemaException;
import com.example.dto.Schema;

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
        // creating an instance of schema with lowercase keys and types
        Schema lowercaseSchema = schema.schemaToLowercase();
        
        if (schema.validateSchemaTypes()) {
            this.currentSchema = lowercaseSchema;
        } else {
            throw new SchemaException("Schema contains one or more invalid type(s). Allowed types are " + Schema.ACCEPTABLE_TYPES,
                    HttpStatus.BAD_REQUEST);
        }
    }
}
