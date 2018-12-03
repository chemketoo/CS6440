package com.fbo.bulk_fhir_server.repository;

import com.fbo.bulk_fhir_server.entity.ObesityMetricEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObesityMetricsRepository extends CrudRepository<ObesityMetricEntity, String> {

    // Clean up query
    @Query(value = "SELECT * FROM obesity_metrics WHERE YEAR(last_updated) = (SELECT YEAR(MAX(last_updated)) FROM obesity_metrics) AND DAY(last_updated) = (SELECT DAY(MAX(last_updated)) FROM obesity_metrics) AND MONTH(last_updated) = (SELECT MONTH(MAX(last_updated)) FROM obesity_metrics) AND HOUR(last_updated) = (SELECT HOUR(MAX(last_updated)) FROM obesity_metrics) AND MINUTE(last_updated) = (SELECT MINUTE(MAX(last_updated)) FROM obesity_metrics);", nativeQuery = true)
    List<ObesityMetricEntity> getLatestObesityMetricEntities();
}
