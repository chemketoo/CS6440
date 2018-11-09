package edu.gatech.curator.service;

import edu.gatech.curator.provider.DateProvider;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SourceSystemServiceTest {

    @MockBean
    private DateProvider mockDateProvider;
    private Date mockedDemarcationDate;

    @Before
    public void setUp() throws Exception {
        mockedDemarcationDate = mock(Date.class);
        when(mockDateProvider.oneWeekFromNow()).thenReturn(mockedDemarcationDate);
    }

    @Test
    @Ignore
    public void pending() { }
}