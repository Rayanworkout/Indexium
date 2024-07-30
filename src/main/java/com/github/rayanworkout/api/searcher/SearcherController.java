package com.github.rayanworkout.api.searcher;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.index.IndexNotFoundException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * The class looks up a movie in the movies index created by {@SimpleIndexer}
 */
@RestController
public class SearcherController {

    @GetMapping(path = "/search", produces = "application/json")
    public ResponseEntity<List<String>> searchDocuments(
            @RequestParam String q, @RequestParam String field) {

        SearcherService searcher = new SearcherService();

        try {
            List<String> result = searcher.search(field, q);

            return ResponseEntity.ok(result);

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IndexNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No data is indexed, you need to index some data first.", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }

}
