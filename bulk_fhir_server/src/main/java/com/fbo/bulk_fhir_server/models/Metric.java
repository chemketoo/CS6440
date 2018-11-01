package com.fbo.bulk_fhir_server.models;

public class Metric {
    private final String source;

    public Metric(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}
