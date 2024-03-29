package com.fbo.bulk_fhir_server.models;

import com.fbo.bulk_fhir_server.entity.ObesityMetricEntity;

import javax.persistence.Entity;

/**
 * ObesityMetric
 */

public class ObesityMetric {

    public final String gender;
    public final int underweight;
    public final int healthy;
    public final int overweight;
    public final int obese;

    public ObesityMetric(ObesityMetricEntity entity) {

        this.gender = entity.getGender();
        this.underweight = entity.getHealthy();
        this.healthy = entity.getHealthy();
        this.overweight = entity.getOverweight();
        this.obese = entity.getObese();
    }
}