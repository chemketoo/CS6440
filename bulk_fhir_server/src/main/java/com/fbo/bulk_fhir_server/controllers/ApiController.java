package com.fbo.bulk_fhir_server.controllers;

import com.fbo.bulk_fhir_server.managers.MetricsManager;
import com.fbo.bulk_fhir_server.managers.SourceManager;
import com.fbo.bulk_fhir_server.models.ApiResponse;

import com.fbo.bulk_fhir_server.models.ObesityGenderYearMetric;
import com.fbo.bulk_fhir_server.models.ObesityMetric;
import com.fbo.bulk_fhir_server.models.SourceMetric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class ApiController {

    @Autowired
    public SourceManager sourceManager;

    @Autowired
    public MetricsManager metricsManager;

    @RequestMapping(method=GET, path="/api/sources")
    public ApiResponse<List<SourceMetric>> sources() {
        List<SourceMetric> sources = sourceManager.getSourceMetrics();
        return new ApiResponse<List<SourceMetric>>(sources);
    }

    @RequestMapping(method=GET, path="/api/metrics/obesity/gender")
    public ApiResponse<List<ObesityMetric>> obesityByGenderMetrics() {
        List<ObesityMetric> metrics = metricsManager.getObesityByGenderMetrics();
        return new ApiResponse<List<ObesityMetric>>(metrics);
    }

    @RequestMapping(method=GET, path="/api/metrics/obesity/year")
    public ApiResponse<List<ObesityGenderYearMetric>> obesityByGenderYearMetrics() {
        List<ObesityGenderYearMetric> metrics = metricsManager.getObesityByGenderYearMetrics();
        return new ApiResponse<List<ObesityGenderYearMetric>>(metrics);
    }
}
