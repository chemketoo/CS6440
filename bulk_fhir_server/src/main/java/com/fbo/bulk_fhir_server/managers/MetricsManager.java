package com.fbo.bulk_fhir_server.managers;

import com.fbo.bulk_fhir_server.entity.ObesityGenderYearMetricEntity;
import com.fbo.bulk_fhir_server.entity.ObesityMetricEntity;
import com.fbo.bulk_fhir_server.models.ObesityGenderYearMetric;
import com.fbo.bulk_fhir_server.models.ObesityMetric;
import com.fbo.bulk_fhir_server.repository.ObesityGenderYearMetricsRepository;
import com.fbo.bulk_fhir_server.repository.ObesityMetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class MetricsManager {

    @Autowired
    ObesityMetricsRepository obesityMetricsRepository;

    @Autowired
    ObesityGenderYearMetricsRepository obesityGenderYearMetricsRepository;

    public List<ObesityMetric> getObesityByGenderMetrics() {
        List<ObesityMetricEntity> results = obesityMetricsRepository.getLatestObesityMetricEntities();

        Iterator<ObesityMetricEntity> iterator =  results.iterator();

        List<ObesityMetric> metrics = new ArrayList<ObesityMetric>();

        while(iterator.hasNext()) {
            ObesityMetricEntity entity = iterator.next();
            metrics.add(new ObesityMetric(entity));
        }

        return metrics;
    }

    public List<ObesityGenderYearMetric> getObesityByGenderYearMetrics() {
        List<ObesityGenderYearMetricEntity> results = obesityGenderYearMetricsRepository.getLatestObesityGenderYearMetrics();

        Iterator<ObesityGenderYearMetricEntity> iterator =  results.iterator();

        List<ObesityGenderYearMetric> metrics = new ArrayList<ObesityGenderYearMetric>();

        while(iterator.hasNext()) {
            ObesityGenderYearMetricEntity entity = iterator.next();
            metrics.add(new ObesityGenderYearMetric(entity));
        }

        return metrics;
    }
}
