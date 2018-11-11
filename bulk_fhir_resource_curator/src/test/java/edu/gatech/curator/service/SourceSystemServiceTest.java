package edu.gatech.curator.service;

import edu.gatech.curator.client.BulkFhirApiClient;
import edu.gatech.curator.entity.SourceSystem;
import edu.gatech.curator.factory.RetrofitClientFactory;
import edu.gatech.curator.fhir.model.AccessTokenResponse;
import edu.gatech.curator.provider.ClientAssertionProvider;
import edu.gatech.curator.provider.DateProvider;
import edu.gatech.curator.repository.SourceSystemsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SourceSystemServiceTest {

    @MockBean
    private DateProvider mockDateProvider;

    @MockBean
    private SourceSystemsRepository mockSourceSystemRepository;

    @MockBean
    private ClientAssertionProvider clientAssertionProvider;

    @MockBean
    private BulkFhirApiClient bulkFhirApiClient;

    @MockBean
    private RetrofitClientFactory retrofitClientFactory;

    @Autowired
    private SourceSystemService subject;

    private Date mockedDemarcationDate;
    private SourceSystem mockSourceSystem;
    private String mockClientAssertion;

    @Before
    public void setUp() throws Exception {
        mockedDemarcationDate = mock(Date.class);
        when(mockDateProvider.oneWeekAgo()).thenReturn(mockedDemarcationDate);

        Date lastUpdated = new SimpleDateFormat("YYYY-MM-dd").parse("2000-02-02");
        mockSourceSystem = new SourceSystem("http://i-need-a.token/auth/token", "a-token-of-some-kind", "key-id-of-jwk", "http://where-jwks-is.at", lastUpdated, null);

        mockClientAssertion = "signed-jwt-to-use-as-client-assertion";
        when(clientAssertionProvider.create(mockSourceSystem)).thenReturn(mockClientAssertion);

        when(retrofitClientFactory.getAPIClient(mockSourceSystem)).thenReturn(bulkFhirApiClient);
    }

    @Test
    public void callsRepositoryToRetrieveSourceSystemsToCurate() {
        List<SourceSystem> sourceSystemsToCurate = mock(List.class);
        when(mockSourceSystemRepository.findAllByLastUpdatedBefore(mockedDemarcationDate)).thenReturn(sourceSystemsToCurate);

        assertThat(subject.retrieveSourceSystemPastDemarcationDate()).isSameAs(sourceSystemsToCurate);
    }

    @Test
    public void getAccessToken() throws ParseException, IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        Call mockCall = mock(Call.class);
        when(mockCall.execute()).thenAnswer((Answer<Response<AccessTokenResponse>>) invocation ->
                Response.success(new AccessTokenResponse("bearer", 900, "expected-access-token")));
        when(bulkFhirApiClient.createAccessToken(
                "system/*.read",
                "client_credentials",
                "urn:ietf:params:oauth:client-assertion-type:jwt-bearer",
                mockClientAssertion)).thenReturn(mockCall);

        String actual = subject.getAccessToken(mockSourceSystem);

        verify(clientAssertionProvider).create(mockSourceSystem);
        verify(retrofitClientFactory).getAPIClient(mockSourceSystem);

        assertThat(actual).isEqualTo("expected-access-token");
    }
}