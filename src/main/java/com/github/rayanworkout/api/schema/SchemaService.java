package com.github.rayanworkout.api.schema;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.github.rayanworkout.api.exception.SchemaException;
import com.github.rayanworkout.dto.Schema;

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
        // Creating an instance of schema with lowercase keys
        Schema lowercaseSchema = schema.schemaToLowercase();
        
        if (schema.validateSchemaTypes()) {
            this.currentSchema = lowercaseSchema;
        } else {
            throw new SchemaException("Schema contains one or more invalid type(s). Allowed types are " + Schema.ACCEPTABLE_TYPES,
                    HttpStatus.BAD_REQUEST);
        }
    }
}
