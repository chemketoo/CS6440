package com.fbo.bulk_fhir_server.repository;

import com.fbo.bulk_fhir_server.models.interfaces.ISourceMetricProjection;
import com.fbo.bulk_fhir_server.entity.PatientEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends CrudRepository<PatientEntity, String> {

    @Query(value = "SELECT source_system_name as sourcesystemname, count(*) as count FROM patient GROUP BY source_system_name", nativeQuery = true)
    List<ISourceMetricProjection> countAllPatientsBySourceSystemName();
}
