package com.fbo.bulk_fhir_server.models;

import com.fbo.bulk_fhir_server.entity.ObesityGenderYearMetricEntity;
import com.fbo.bulk_fhir_server.entity.ObesityMetricEntity;

/**
 * ObesityGenderYearMetric
 */

public class ObesityGenderYearMetric {

    public final String gender;
    public final int underweight;
    public final int healthy;
    public final int overweight;
    public final int obese;
    public final String age;
    public final int year;

    public ObesityGenderYearMetric(ObesityGenderYearMetricEntity entity) {

        this.gender = entity.getGender();
        this.age = entity.getAge();
        this.year = entity.getYear();
        this.underweight = entity.getHealthy();
        this.healthy = entity.getHealthy();
        this.overweight = entity.getOverweight();
        this.obese = entity.getObese();
    }
}
