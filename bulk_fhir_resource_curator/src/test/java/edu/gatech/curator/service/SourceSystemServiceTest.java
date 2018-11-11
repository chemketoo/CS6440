package edu.gatech.curator.service;

import edu.gatech.curator.entity.SourceSystem;
import edu.gatech.curator.provider.DateProvider;
import edu.gatech.curator.repository.SourceSystemsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SourceSystemServiceTest {

    @MockBean
    private DateProvider mockDateProvider;

    @MockBean
    private SourceSystemsRepository mockSourceSystemRepository;

    @Autowired
    private SourceSystemService subject;

    private Date mockedDemarcationDate;

    @Before
    public void setUp() throws Exception {
        mockedDemarcationDate = mock(Date.class);
        when(mockDateProvider.oneWeekAgo()).thenReturn(mockedDemarcationDate);

    }

    @Test
    public void callsRepositoryToRetrieveSourceSystemsToCurate() {
        List<SourceSystem> sourceSystemsToCurate = mock(List.class);
        when(mockSourceSystemRepository.findAllByLastUpdatedBefore(mockedDemarcationDate)).thenReturn(sourceSystemsToCurate);

        assertThat(subject.retrieveSourceSystemPastDemarcationDate()).isSameAs(sourceSystemsToCurate);
    }
}