package edu.gatech.curator.repository;

import edu.gatech.curator.entity.SourceSystem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {RepositoryTestConfiguration.class})
public class SourceSystemsRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SourceSystemsRepository subject;

    @Test
    public void shouldReturnSourceSystemsOlderThanGivenDate() throws ParseException {
        Date demarcationDate = new SimpleDateFormat("yyyy-MM-dd").parse("2018-02-01");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2000-02-01");

        SourceSystem entity = new SourceSystem();
        entity.setAccessToken("-");
        entity.setClientId("-");
        entity.setJku("-");
        entity.setKid("-");
        entity.setLastUpdated(date);
        entity.setLocation("-");

        entityManager.persist(entity);

        List<SourceSystem> actual = subject.findAllByLastUpdatedBefore(demarcationDate);

        assertThat(actual).hasSize(2);
    }
}

