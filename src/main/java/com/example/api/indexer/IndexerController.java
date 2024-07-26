package com.example.api.indexer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.RawDocument;
import com.example.api.dto.Schema;
import com.example.api.exception.IndexingRequestException;

import java.io.IOException;
import java.util.Map;

@RestController
public class IndexerController {

    private final IndexerService indexer;
    private Schema currentSchema;

    public IndexerController() throws IOException {
        this.indexer = new IndexerService();
        this.currentSchema = null;
    }

    // REGISTER A NEW SCHEMA
    @PostMapping(path = "/schema/set", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> defineSchema(@RequestBody Schema schema) {
        this.currentSchema = schema;
        return ResponseEntity.ok("Schema defined successfully.");
    }

    // GET THE CURRENT SCHEMA
    @GetMapping(path = "/schema/get", produces = "application/json")
    public ResponseEntity<Object> getSchema() {
        if (this.currentSchema == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No schema is defined. Make a POST request to /schema/set to create one.");
        }

        return ResponseEntity.ok(this.currentSchema.getFields());
    }

    @PostMapping(path = "/index", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> indexDocument(@RequestBody RawDocument rawDoc) throws IOException {
        try {

            if (this.currentSchema == null) {
                throw new IndexingRequestException(
                        "You need to set a schema before sending any document. Send a POST request to /schema/set to register the current schema.");
            }

            if (rawDoc.getData() == null) {
                throw new IndexingRequestException(
                        "You need to provide a title entry to index that must not be empty.");
            }

            indexer.index(rawDoc);

            return ResponseEntity.ok("Successfully indexed: " + rawDoc.getTitle());

        } catch (IOException e) {
            throw new IndexingRequestException("Failed to index document: " + rawDoc.getData(), e);
        }
    }
}
