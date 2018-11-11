package edu.gatech.curator.fhir.model;

import edu.gatech.curator.testhelper.JsonFromResource;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;


import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AllergyIntoleranceSerializationTest extends FhirModelSerializationTest {

    @Value("classpath:test_fixtures/AllergyIntolerance.json")
    private Resource allergyIntolerance;

    @Test
    @Ignore
    public void pending() throws IOException {
//        AllergyIntolerance serialized = objectMapper.readValue(JsonFromResource.getBytes(allergyIntolerance), AllergyIntolerance.class);
    }
}
