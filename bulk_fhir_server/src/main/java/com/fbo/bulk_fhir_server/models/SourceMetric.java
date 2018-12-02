package com.fbo.bulk_fhir_server.models;

import javax.persistence.Entity;

/**
 * SourceMetric
 */

public class SourceMetric {
    public String sourceSystemName;
    public int count;

    public SourceMetric(String sourceSystemName, int count) {
        this.sourceSystemName = sourceSystemName;
        this.count = count;
    }
}