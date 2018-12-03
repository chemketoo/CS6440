package com.fbo.bulk_fhir_server.repository;

import com.fbo.bulk_fhir_server.entity.ObesityGenderYearMetricEntity;
import com.fbo.bulk_fhir_server.entity.ObesityMetricEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObesityGenderYearMetricsRepository extends CrudRepository<ObesityGenderYearMetricEntity, String> {

    // Clean up query
    @Query(value = "SELECT * FROM obesity_metrics_gender_year WHERE YEAR(last_updated) = (SELECT YEAR(MAX(last_updated)) FROM obesity_metrics_gender_year) AND DAY(last_updated) = (SELECT DAY(MAX(last_updated)) FROM obesity_metrics_gender_year) AND MONTH(last_updated) = (SELECT MONTH(MAX(last_updated)) FROM obesity_metrics_gender_year) AND HOUR(last_updated) = (SELECT HOUR(MAX(last_updated)) FROM obesity_metrics_gender_year) AND MINUTE(last_updated) = (SELECT MINUTE(MAX(last_updated)) FROM obesity_metrics_gender_year);", nativeQuery = true)
    List<ObesityGenderYearMetricEntity> getLatestObesityGenderYearMetrics();
}
