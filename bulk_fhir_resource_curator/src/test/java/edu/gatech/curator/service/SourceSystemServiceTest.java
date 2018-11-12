package edu.gatech.curator.service;

import edu.gatech.curator.client.BulkFhirApiClient;
import edu.gatech.curator.entity.SourceSystem;
import edu.gatech.curator.factory.RetrofitClientFactory;
import edu.gatech.curator.fhir.model.AccessTokenResponse;
import edu.gatech.curator.fhir.model.OperationOutcome;
import edu.gatech.curator.provider.ClientAssertionProvider;
import edu.gatech.curator.provider.DateProvider;
import edu.gatech.curator.provider.OperationOutcomeTextUrlProvider;
import edu.gatech.curator.repository.SourceSystemsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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

    @MockBean
    private OperationOutcomeTextUrlProvider operationOutcomeTextUrlProvider;

    @Autowired
    private SourceSystemService subject;

    @Mock
    private Call mockCall;

    private Date mockedDemarcationDate;
    private SourceSystem mockSourceSystem;
    private String mockClientAssertion;
    private String accessToken;

    @Before
    public void setUp() throws Exception {
        accessToken = "expected-access-token";

        mockedDemarcationDate = mock(Date.class);
        when(mockDateProvider.oneWeekAgo()).thenReturn(mockedDemarcationDate);

        Date lastUpdated = new SimpleDateFormat("YYYY-MM-dd").parse("2000-02-02");
        mockSourceSystem = new SourceSystem("http://i-need-a.token/auth/token", "a-token-of-some-kind", "key-id-of-jwk", "http://where-jwks-is.at", lastUpdated, accessToken);

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
    public void getAccessToken() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        when(mockCall.execute()).thenAnswer((Answer<Response<AccessTokenResponse>>) invocation ->
                Response.success(new AccessTokenResponse("bearer", 900, accessToken)));
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

    @Test
    public void startPatientExportOperation() throws IOException {
        OperationOutcome operationOutcome = mock(OperationOutcome.class);
        OperationOutcome.Text mockOperationOutcomeText = mock(OperationOutcome.Text.class);
        when(operationOutcome.getText()).thenReturn(mockOperationOutcomeText);
        when(mockOperationOutcomeText.getDiv()).thenReturn("div-string");

        URL expectedUrl = new URL("http://test.pass");
        when(operationOutcomeTextUrlProvider.parse("div-string")).thenReturn(expectedUrl);

        when(mockCall.execute()).thenAnswer((Answer<Response<OperationOutcome>>) invocation -> Response.success(operationOutcome));

        String authorization = "bearer " + accessToken;
        when(bulkFhirApiClient.startPatientExportOperation(authorization, "application/fhir+ndjson")).thenReturn(mockCall);

        URL actual = subject.startPatientExportOperation(mockSourceSystem);

        verify(retrofitClientFactory).getAPIClient(mockSourceSystem);
        assertThat(actual).isSameAs(expectedUrl);
    }

    @Test
    public void getExportOutputs() throws MalformedURLException {
        subject.getExportOutputs(new URL("http://example.net"), mockSourceSystem);
    }
}