package com.github.rayanworkout.api.schema;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
    private Schema currentSchema = new Schema(new HashMap<>());

    Optional<Map<String, String>> optionalFields = Optional.ofNullable(currentSchema.getFields());

    Map<String, String> fields = optionalFields.orElse(new HashMap<>());

    public Schema getCurrentSchema() {
        return currentSchema.getFields() == null || currentSchema.getFields().isEmpty() ? null : currentSchema;
    }

    public void setCurrentSchema(Schema schemaCandidate) {

        Optional<Map<String, String>> optionalCurrentSchema = Optional.ofNullable(schemaCandidate.getFields());

        optionalCurrentSchema.ifPresentOrElse(
                fields -> {
                    // Creating an instance of schema with lowercase keys
                    Schema lowercaseSchema = schemaToLowercase(new Schema(fields));

                    if (!validateSchemaTypes(lowercaseSchema)) {
                        throw new SchemaException(
                                "Schema contains one or more invalid type(s). Allowed types are "
                                        + Schema.ACCEPTABLE_TYPES,
                                HttpStatus.BAD_REQUEST);
                    }

                    // If everything is ok, I assign the new schema
                    this.currentSchema = lowercaseSchema;
                },
                () -> throwSchemaException("Schema is empty. Please provide some fields."));

    }

    private boolean validateSchemaTypes(Schema fieldsToCheck) {
        // Validate that all fields have types that are acceptable
        return fieldsToCheck.getFields()
                .values()
                .stream()
                .map(String::trim)
                .map(e -> StringMethods.capitalize(e))
                .allMatch(Schema.ACCEPTABLE_TYPES::contains);
    }

    /**
     * Returns a new Schema object with lowercase keys in the fields map.
     */
    private Schema schemaToLowercase(Schema schema) {
        return new Schema(
                schema.getFields().entrySet()
                        .stream()
                        .map(e -> new AbstractMap.SimpleEntry<String, String>(
                                e.getKey().toLowerCase(), e.getValue().toLowerCase()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

    }

    private void throwSchemaException(String message) {
        throw new SchemaException(
                message,
                HttpStatus.BAD_REQUEST);
    }

}
