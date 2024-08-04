package com.github.rayanworkout.dto;

import java.util.Map;

public class RawDocument {

    private Map<String, Object> data;

    public Map<String, Object> getData() {
        return (data == null || data.isEmpty()) ? null : data;
    }

}
