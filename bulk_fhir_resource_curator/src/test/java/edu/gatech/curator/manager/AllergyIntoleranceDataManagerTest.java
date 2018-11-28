package edu.gatech.curator.manager;

import org.hl7.fhir.dstu3.model.AllergyIntolerance;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AllergyIntoleranceDataManagerTest {

    @Autowired AllergyIntoleranceDataManager subject;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @Ignore
    public void save() {
        List<AllergyIntolerance> listOfAllergyIntolerances = new ArrayList<>();
        listOfAllergyIntolerances.add(new AllergyIntolerance());
        subject.save(listOfAllergyIntolerances);
    }
}