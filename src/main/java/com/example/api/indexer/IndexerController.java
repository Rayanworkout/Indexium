package com.example.api.indexer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.RawDocument;
import com.example.api.exception.IndexingRequestException;

import java.io.IOException;

@RestController
public class IndexerController {

    private final IndexerService indexer;

    public IndexerController() throws IOException {
        this.indexer = new IndexerService();
    }

    @PostMapping(path = "/index", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> indexDocument(@RequestBody RawDocument rawDoc) throws IOException {
        try {

            if (rawDoc.getData() == null) {
                throw new IndexingRequestException(
                        "You need to provide a title entry to index that must not be empty.");
            }

            System.out.println(rawDoc.getData());

            indexer.index(rawDoc);
            
            return ResponseEntity.ok("Indexed document: " + rawDoc.getData());
        } catch (IOException e) {
            throw new IndexingRequestException("Failed to index document: " + rawDoc.getData(), e);
        }
    }
}
