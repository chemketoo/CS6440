package edu.gatech.curator.fhir.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.gatech.curator.service.CuratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

public abstract class FhirModelSerializationTest {

    @MockBean
    CuratorService curatorService;

    @Autowired
    ApplicationContext context;

    protected ObjectMapper objectMapper;

    protected FhirModelSerializationTest () {
        objectMapper = new ObjectMapper();
    }
}
