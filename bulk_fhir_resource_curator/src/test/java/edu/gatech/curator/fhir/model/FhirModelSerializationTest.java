package edu.gatech.curator.fhir.model;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class FhirModelSerializationTest {
    protected ObjectMapper objectMapper;

    protected FhirModelSerializationTest () {
        objectMapper = new ObjectMapper();
    }
}
