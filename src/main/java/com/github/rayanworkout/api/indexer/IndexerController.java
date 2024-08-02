package com.github.rayanworkout.api.indexer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.rayanworkout.api.schema.SchemaService;
import com.github.rayanworkout.api.schema.SchemaValidator;
import com.github.rayanworkout.dto.RawDocument;
import com.github.rayanworkout.dto.Schema;
import com.github.rayanworkout.dto.SuccessResponse;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class IndexerController {

    private final IndexerService indexer;

    private final SchemaService schemaService;

    @PostMapping(path = "/index", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> indexDocument(@RequestBody RawDocument rawDoc) throws IOException {

        Optional<Schema> optionalCurrentSchema = Optional.ofNullable(schemaService.getCurrentSchema());

        optionalCurrentSchema.ifPresentOrElse(

                schema -> {
                    boolean isSchemaValid = SchemaValidator.validate(rawDoc, schema);

                    if (!isSchemaValid) {
                        indexer.throwIndexingException(
                                "Your document need to match the current schema. You can fetch /schema/get to see it.");
                    }

                    try {

                        indexer.index(rawDoc);

                    } catch (IOException e) {
                        indexer.throwIndexingException(
                                "Failed to index document: " + rawDoc.getData());
                    }

                },

                () -> indexer.throwIndexingException(
                        "You need to set a schema before sending any document. Send a POST request to /schema/set to register the current schema."));

        return ResponseEntity.ok(
                new SuccessResponse("Successfully indexed: ", HttpStatus.CREATED));
    }
}
