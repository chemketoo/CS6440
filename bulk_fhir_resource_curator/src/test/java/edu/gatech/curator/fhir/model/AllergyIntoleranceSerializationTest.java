package edu.gatech.curator.fhir.model;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AllergyIntoleranceSerializationTest extends FhirModelSerializationTest {

    @Value("classpath:test_fixtures/AllergyIntolerance.json")
    private Resource allergyIntolerance;

    @Test
    @Ignore
    public void pending() throws IOException {
//        AllergyIntolerance serialized = objectMapper.readValue(JsonFromResource.getBytes(allergyIntolerance), AllergyIntolerance.class);
    }
}
