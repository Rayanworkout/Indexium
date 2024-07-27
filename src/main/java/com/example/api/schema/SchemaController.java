package com.example.api.schema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.Schema;


@RestController
public class SchemaController {

    @Autowired
    private SchemaService schemaService;

    // REGISTER A NEW SCHEMA
    @PostMapping(path = "/schema/set", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> defineSchema(@RequestBody Schema schema) {
        schemaService.setCurrentSchema(schema);
        return ResponseEntity.ok("Schema defined successfully.");
    }

    // GET THE CURRENT SCHEMA
    @GetMapping(path = "/schema/get", produces = "application/json")
    public ResponseEntity<Object> getSchema() {
        Schema currentSchema = schemaService.getCurrentSchema();
        if (currentSchema == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No schema is defined. Make a POST request to /schema/set to create one.");
        }

        return ResponseEntity.ok(currentSchema.getFields());
    }

}
