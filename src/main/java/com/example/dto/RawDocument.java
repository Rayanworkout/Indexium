package com.example.dto;

import java.util.Map;

public class RawDocument {

    private Map<String, Object> data;

    public String getTitle() {
        if (data != null && data.get("title") instanceof String) {
            return (String) data.get("title");
        }
        return null;
    }

    public Map<String, Object> getData() {
        return data;
    }

}
