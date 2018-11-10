package com.fbo.bulk_fhir_server.controllers;

import com.fbo.bulk_fhir_server.models.ApiResponse;
import com.fbo.bulk_fhir_server.models.Metric;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MetricController {

    @RequestMapping(method=GET, path="/api/metrics")
    public ApiResponse metrics(@RequestParam(value="source", defaultValue="census") String source) {
        ArrayList list = new ArrayList();
        list.add(new Metric(source));
        return new ApiResponse(list);
    }

}
