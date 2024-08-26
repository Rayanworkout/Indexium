package com.github.rayanworkout.dto;

import java.util.List;

public class FoundDocument {
    private int hits;
    private String field;
    private List<String> result;

    public FoundDocument(int hits, String field, List<String> result) {
        this.hits = hits;
        this.field = field;
        this.result = result;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
