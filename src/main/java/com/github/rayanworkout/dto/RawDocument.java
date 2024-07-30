package com.github.rayanworkout.dto;

import java.util.HashMap;
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
        
        Map<String, Object> lowerCaseData = new HashMap<>();
        
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            lowerCaseData.put(entry.getKey().toLowerCase(), entry.getValue());
        }
        return lowerCaseData;
    }

}
