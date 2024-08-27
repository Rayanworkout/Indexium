package com.github.rayanworkout.api.searcher;

import org.springframework.web.bind.annotation.RestController;

import com.github.rayanworkout.dto.SearchResult;

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
    public ResponseEntity<SearchResult> searchDocuments(
            @RequestParam String q, @RequestParam String field) {

        SearcherService searcher = new SearcherService();

        try {
            List<String> result = searcher.search(field, q);

            return ResponseEntity.ok(
                    new SearchResult(
                            result.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND,
                            result.size(),
                            field,
                            result));

        } catch (ParseException e) {
            searcher.throwSearchException(
                    "Could not parse the query. Please check the query syntax.");
        } catch (IndexNotFoundException e) {
            searcher.throwSearchException(
                    "No data is indexed, you need to index some data first.");
        } catch (IOException e) {
            searcher.throwSearchException(
                    "Failed to search for documents within the index.");
        } catch (Exception e) {
            searcher.throwSearchException(
                    "An unexpected error occurred while searching for documents: " + e.getMessage());
        }

        return null;

    }

}
