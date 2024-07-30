package com.github.rayanworkout.api.indexer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.rayanworkout.api.exception.IndexingException;
import com.github.rayanworkout.api.schema.SchemaService;
import com.github.rayanworkout.api.schema.SchemaValidator;
import com.github.rayanworkout.dto.RawDocument;
import com.github.rayanworkout.dto.Schema;
import com.github.rayanworkout.dto.SuccessResponse;

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
    public ResponseEntity<SuccessResponse> indexDocument(@RequestBody RawDocument rawDoc) throws IOException {
        try {
            Schema currentSchema = schemaService.getCurrentSchema();
            if (currentSchema == null) {
                throw new IndexingException(
                        "You need to set a schema before sending any document. Send a POST request to /schema/set to register the current schema.",
                        HttpStatus.BAD_REQUEST);
            }

            boolean isValid = SchemaValidator.validate(rawDoc, currentSchema);

            if (!isValid) {
                throw new IndexingException(
                        "Your document need to match the current schema. You can fetch /schema/get to see it.",
                        HttpStatus.BAD_REQUEST);
            }

            indexer.index(rawDoc);

            SuccessResponse response = new SuccessResponse("Successfully indexed: ", HttpStatus.CREATED);

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            throw new IndexingException(
                    "Failed to index document: " + rawDoc.getData(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
