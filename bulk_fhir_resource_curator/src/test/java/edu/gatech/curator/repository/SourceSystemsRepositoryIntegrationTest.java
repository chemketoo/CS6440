package edu.gatech.curator.repository;

import edu.gatech.curator.entity.SourceSystem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

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
        Date demarcationDate = new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("1999-12-31");

        SourceSystem entity1 = new SourceSystem("location-1", "clientId", "kid", "jku", date, "access-token");
        SourceSystem entity2 = new SourceSystem("location-2", "clientId", "kid", "jku", date, "access-token");
        SourceSystem entity3 = new SourceSystem("location-3", "clientId", "kid", "jku", demarcationDate, "access-token");

        entityManager.persist(entity1);
        entityManager.persist(entity2);
        entityManager.persist(entity3);

        List<SourceSystem> actual = subject.findAllByLastUpdatedBefore(demarcationDate);

        assertThat(actual).hasSize(2);
        assertThat(actual).containsExactlyInAnyOrder(entity1, entity2);
    }
}

