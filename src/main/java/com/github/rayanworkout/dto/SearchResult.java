package com.github.rayanworkout.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

public class SearchResult {
    private HttpStatus httpStatus;
    private int hits;
    private String field;
    private List<String> results;

    public SearchResult(HttpStatus httpStatus, int hits, String field, List<String> results) {
        this.httpStatus = httpStatus;
        this.hits = hits;
        this.field = field;
        this.results = results;
    }

    public int getHits() {
        return hits;
    }

    public String getField() {
        return field;
    }

    public List<String> getResult() {
        return results;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
