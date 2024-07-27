package com.example.api.indexer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.RawDocument;
import com.example.api.dto.Schema;
import com.example.api.exception.IndexingException;
import com.example.api.schema.SchemaService;

import java.io.IOException;

@RestController
public class IndexerController {

    private final IndexerService indexer;

    @Autowired
    private SchemaService schemaService;

    public IndexerController() throws IOException {
        this.indexer = new IndexerService();
    }

    @PostMapping(path = "/index", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> indexDocument(@RequestBody RawDocument rawDoc) throws IOException {
        try {
            Schema currentSchema = schemaService.getCurrentSchema();
            if (currentSchema == null) {
                throw new IndexingException(
                        "You need to set a schema before sending any document. Send a POST request to /schema/set to register the current schema.",
                        HttpStatus.BAD_REQUEST);
            }

            if (rawDoc.getData() == null) {
                throw new IndexingException(
                        "You need to provide a title entry to index that must not be empty.",
                        HttpStatus.BAD_REQUEST);
            }

            indexer.index(rawDoc);

            return ResponseEntity.ok("Successfully indexed: " + rawDoc.getTitle());

        } catch (IOException e) {
            throw new IndexingException("Failed to index document: " + rawDoc.getData(),
            HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
