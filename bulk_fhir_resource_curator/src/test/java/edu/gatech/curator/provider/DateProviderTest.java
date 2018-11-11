package edu.gatech.curator.provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateProviderTest {
    @Autowired
    DateProvider subject;

    @Test
    public void providesDateOneWeekAgo() {
        long DAY_IN_MS = 1000 * 60 * 60 * 24;

        assertThat(subject.oneWeekAgo()).isInSameSecondWindowAs(new Date(System.currentTimeMillis() - (7 * DAY_IN_MS)));
    }
}