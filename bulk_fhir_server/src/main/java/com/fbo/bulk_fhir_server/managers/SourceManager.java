package com.fbo.bulk_fhir_server.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fbo.bulk_fhir_server.models.interfaces.ISourceMetricProjection;
import com.fbo.bulk_fhir_server.models.SourceMetric;

import com.fbo.bulk_fhir_server.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SourceManager
 */
@Component("SourceManager")
public class SourceManager {

    @Autowired
    private PatientRepository patientRepository;

    public List<SourceMetric> getSourceMetrics() {
        List<ISourceMetricProjection> projections = patientRepository.countAllPatientsBySourceSystemName();

        List<SourceMetric> sms = new ArrayList<SourceMetric>();

        Iterator<ISourceMetricProjection> iterator = projections.iterator();

        while (iterator.hasNext()) {
           ISourceMetricProjection projection = iterator.next();
           projection.getSourceSystemName();
           sms.add(new SourceMetric(projection.getSourceSystemName(), projection.getCount()));
        }

        return sms;
    }
}
