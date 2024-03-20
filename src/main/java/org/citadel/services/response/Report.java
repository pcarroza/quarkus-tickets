package org.citadel.services.response;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class Report {

    private final Map<String, Object> report;

    public Report() {
        report = new LinkedHashMap<>();
    }

    public Report put(String key, Object value) {
        report.put(key, value);
        return this;
    }
}
