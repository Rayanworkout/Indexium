package com.github.rayanworkout.api.schema;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.github.rayanworkout.api.exception.SchemaException;
import com.github.rayanworkout.dto.Schema;
import com.github.rayanworkout.helpers.StringMethods;

/**
 * Shared service between the IndexerController and SchemaController;
 * It allows me to access and modify a single instance of schema
 * It is a kind of dependency injection
 */
@Service
public class SchemaService {
    private Schema currentSchema;

    Map<String, String> fields = currentSchema.getFields();

    public Schema getCurrentSchema() {
        return currentSchema.getFields() == null ? currentSchema : null;
    }

    public void setCurrentSchema(Schema schema) {

        if (schema.getFields() == null || schema.getFields().isEmpty()) {
            throw new SchemaException(
                    "Schema is empty. Please provide some fields.",
                    HttpStatus.BAD_REQUEST);
        }

        // Creating an instance of schema with lowercase keys
        Schema lowercaseSchema = schemaToLowercase();

        if (!validateSchemaTypes()) {
            throw new SchemaException(
                    "Schema contains one or more invalid type(s). Allowed types are " + Schema.ACCEPTABLE_TYPES,
                    HttpStatus.BAD_REQUEST);
        }

        // If everything is ok, I assign the new schema
        this.currentSchema = lowercaseSchema;
    }

    private boolean validateSchemaTypes() {

        // Validate that all capitalized types are acceptable
        return fields.values()
                .stream()
                .map(e -> StringMethods.capitalize(e))
                .allMatch(Schema.ACCEPTABLE_TYPES::contains);
    }

    /**
     * Returns a new Schema object with lowercase keys in the fields map.
     */
    private Schema schemaToLowercase() {
        return new Schema(
                fields.entrySet()
                        .stream()
                        .map(e -> new AbstractMap.SimpleEntry<String, String>(
                                e.getKey().toLowerCase(), e.getValue().toLowerCase()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

    }
}
