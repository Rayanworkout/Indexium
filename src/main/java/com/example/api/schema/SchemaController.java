package com.example.api.schema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.exception.SchemaException;
import com.example.dto.Schema;
import com.example.dto.SuccessResponse;

import jakarta.validation.Valid;

@RestController
public class SchemaController {

    @Autowired
    private SchemaService schemaService;

    // REGISTER A NEW SCHEMA
    @PostMapping(path = "/schema/set", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> defineSchema(@Valid @RequestBody Schema schema) {


        schemaService.setCurrentSchema(schema);

        SuccessResponse response = new SuccessResponse("Schema defined successfully.", HttpStatus.CREATED);

        return ResponseEntity.ok(response);
    }

    // GET THE CURRENT SCHEMA
    @GetMapping(path = "/schema/get", produces = "application/json")
    public ResponseEntity<Object> getSchema() {
        Schema currentSchema = schemaService.getCurrentSchema();
        if (currentSchema == null) {
            throw new SchemaException(
                    "No schema is defined. Make a POST request to /schema/set to create one.",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(currentSchema.getFields());
    }

}
