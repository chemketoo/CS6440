package edu.gatech.curator.factory;

import edu.gatech.curator.client.BulkFhirApiClient;
import edu.gatech.curator.entity.SourceSystem;
import edu.gatech.curator.service.CuratorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RetrofitClientFactoryTest {

    @MockBean
    CuratorService service;

    @Autowired
    ApplicationContext context;

    @Autowired
    RetrofitClientFactory subject;

    private SourceSystem sourceSystem;

    @Before
    public void setUp() throws Exception {
        Date date = new SimpleDateFormat("YYYY-MM-dd").parse("2000-01-01");
        sourceSystem = new SourceSystem("https://enigmatic-waters-34317.herokuapp.com", "random-string", "kid", "adfdsaf", date, "hdafds");
    }

    @Test
    public void getAPIClient() throws MalformedURLException {
        BulkFhirApiClient client =  subject.getAPIClient(sourceSystem);

        assertThat(client).isNotNull();
    }
}