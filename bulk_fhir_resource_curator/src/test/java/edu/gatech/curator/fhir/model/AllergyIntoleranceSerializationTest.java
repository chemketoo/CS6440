package edu.gatech.curator.fhir.model;

import edu.gatech.curator.testhelper.JsonFromResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AllergyIntoleranceSerializationTest extends FhirModelSerializationTest {

    @Value("classpath:test_fixtures/AllergyIntolerance.json")
    private Resource allergyIntolerance;

    @Test
    public void objectSerializationTest() throws IOException, ParseException {
        AllergyIntolerance serialized = objectMapper.readValue(JsonFromResource.getBytes(allergyIntolerance), AllergyIntolerance.class);

        Date expectedDate = new SimpleDateFormat("yyyy-MM-dd'T'hh':'mm':'ss").parse("1955-12-16T04:05:19-05:00");

        assertThat(serialized.getResourceType()).isEqualTo("AllergyIntolerance");
        assertThat(serialized.getId()).isEqualTo("0030ae21-71fe-409b-8d50-b155e46a0944");
        assertThat(serialized.getMeta().getProfile().get(0)).isEqualTo("http://standardhealthrecord.org/fhir/StructureDefinition/shr-allergy-AllergyIntolerance");
        assertThat(serialized.getClinicalStatus()).isEqualTo("active");
        assertThat(serialized.getVerificationStatus()).isEqualTo("confirmed");
        assertThat(serialized.getType()).isEqualTo("allergy");
        assertThat(serialized.getCategory().get(0)).isEqualTo("food");
        assertThat(serialized.getCriticality()).isEqualTo("low");
        assertThat(serialized.getCode().getCoding().get(0).getSystem()).isEqualTo("http://snomed.info/sct");
        assertThat(serialized.getCode().getCoding().get(0).getCode()).isEqualTo("300913006");
        assertThat(serialized.getCode().getCoding().get(0).getDisplay()).isEqualTo("Shellfish allergy");
        assertThat(serialized.getCode().getText()).isEqualTo("Shellfish allergy");
        assertThat(serialized.getPatient().getReference()).isEqualTo("Patient/876e6665-2d8e-432b-9bda-2c9b580c8b18");
        assertThat(serialized.getAssertedDate()).isEqualToIgnoringMillis(expectedDate);
    }
}
