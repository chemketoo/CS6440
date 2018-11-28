package edu.gatech.curator.service;

import edu.gatech.curator.client.BulkFhirApiClient;
import edu.gatech.curator.entity.SourceSystem;
import edu.gatech.curator.factory.RetrofitClientFactory;
import edu.gatech.curator.manager.AllergyIntoleranceDataManager;
import edu.gatech.curator.manager.CarePlanDataManager;
import edu.gatech.curator.model.ExportOutputResponse;
import edu.gatech.curator.model.NdJson;
import okhttp3.HttpUrl;
import org.hl7.fhir.dstu3.model.AllergyIntolerance;
import org.hl7.fhir.dstu3.model.CarePlan;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FhirResourceProcessorServiceTest {

    @MockBean
    private SourceSystemService mockSourceSystemService;

    @MockBean
    private RetrofitClientFactory retrofitClientFactory;

    @MockBean
    private BulkFhirApiClient bulkApiClient;

    @MockBean
    private AllergyIntoleranceDataManager allergyIntoleranceDataManager;

    @MockBean
    private CarePlanDataManager carePlanDataManager;

    @Autowired
    FhirResourceProcessorService subject;
    private String urlString;
    private HttpUrl url;
    private List<ExportOutputResponse.ExportOutput> exports;
    private String accessToken;
    private SourceSystem sourceSystem;
    private String authorization;
    private NdJson<?> ndJson;
    private List listOfResources;

    @Before
    public void setUp() throws MalformedURLException {
        when(retrofitClientFactory.getAPIClient(any(SourceSystem.class))).thenReturn(bulkApiClient);
        urlString = "http://example-server.net";
        url = HttpUrl.parse(urlString);
        exports = new ArrayList<>();
        accessToken = "access-token";
        sourceSystem = mock(SourceSystem.class);
        when(sourceSystem.getAccessToken()).thenReturn(accessToken);
        authorization = "bearer " + accessToken;
        ndJson = mock(NdJson.class);
        listOfResources = mock(List.class);
        when(ndJson.getResources()).thenReturn(listOfResources);
    }

    @Test
    public void process_retrievesAllergyIntoleranceResourcesFromBulkFhirApi() throws IOException {
        exports.add(new ExportOutputResponse.ExportOutput("AllergyIntolerance", 1, urlString));
        Call<NdJson<AllergyIntolerance>> mockCall = mock(Call.class);
        when(mockCall.execute()).thenAnswer(invocation -> Response.success(ndJson));
        when(bulkApiClient.getAllergyIntoleranceResource(url, authorization)).thenReturn(mockCall);

        subject.process(exports, sourceSystem);

        verify(bulkApiClient).getAllergyIntoleranceResource(url, authorization);
        verify(allergyIntoleranceDataManager).save(listOfResources);
    }

    @Test
    public void process_retrievesCarePlanResourcesFromBulkFhirApi() throws IOException {
        exports.add(new ExportOutputResponse.ExportOutput("CarePlan", 1, urlString));
        Call<NdJson<CarePlan>> mockCall = mock(Call.class);
        when(mockCall.execute()).thenAnswer(invocation -> Response.success(ndJson));
        when(bulkApiClient.getCarePlanResources(url, authorization)).thenReturn(mockCall);

        subject.process(exports, sourceSystem);

        verify(bulkApiClient).getCarePlanResources(url, authorization);
        verify(carePlanDataManager).save(listOfResources);
    }
}