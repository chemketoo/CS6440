package com.fbo.bulk_fhir_server.repository;

import com.fbo.bulk_fhir_server.entity.ObesityMetricEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObesityMetricsRepository extends CrudRepository<ObesityMetricEntity, String> {

    @Query(value = "SELECT * FROM obesity_metrics WHERE last_updated=(SELECT MAX(last_updated) FROM obesity_metrics);", nativeQuery = true)
    List<ObesityMetricEntity> getLatestObesityMetricEntities();
}
