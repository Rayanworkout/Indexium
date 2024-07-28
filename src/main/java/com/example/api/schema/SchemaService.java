package com.example.api.schema;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.api.dto.Schema;
import com.example.api.exception.SchemaException;

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

        if (schema.validateSchemaTypes()) {
            this.currentSchema = schema;
        } else {
            throw new SchemaException("Schema contains invalid types.", HttpStatus.BAD_REQUEST);
        }
    }
}
