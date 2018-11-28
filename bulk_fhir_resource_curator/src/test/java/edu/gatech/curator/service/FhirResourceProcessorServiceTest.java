package edu.gatech.curator.service;

import edu.gatech.curator.client.BulkFhirApiClient;
import edu.gatech.curator.entity.SourceSystem;
import edu.gatech.curator.factory.RetrofitClientFactory;
import edu.gatech.curator.manager.AllergyIntoleranceDataManager;
import edu.gatech.curator.model.ExportOutputResponse;
import edu.gatech.curator.model.NdJson;
import okhttp3.HttpUrl;
import org.hl7.fhir.dstu3.model.AllergyIntolerance;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FhirResourceProcessorServiceTest {

    @MockBean
    private RetrofitClientFactory retrofitClientFactory;

    @MockBean
    private BulkFhirApiClient bulkApiClient;

    @MockBean
    private SourceSystemService mockSourceSystemService;

    @MockBean
    private AllergyIntoleranceDataManager allergyIntoleranceDataManager;

    @Autowired
    FhirResourceProcessorService subject;

    @Before
    public void setUp() throws MalformedURLException {
        when(retrofitClientFactory.getAPIClient(any(SourceSystem.class))).thenReturn(bulkApiClient);
    }

    @Test
    public void startsTheProcessCuratingEachFhirResourceType() throws ParseException, IOException {
        String urlString = "http://example-server.net";
        HttpUrl url = HttpUrl.parse(urlString);

        List<ExportOutputResponse.ExportOutput> exports = new ArrayList<>();
        exports.add(new ExportOutputResponse.ExportOutput("AllergyIntolerance", 10, urlString));

        Date date = new SimpleDateFormat("YYYY-MM-dd").parse("2018-02-01");

        String accessToken = "access-token";
        SourceSystem sourceSystem = new SourceSystem("loc", "cId", "kid", "jku", date, accessToken);

        Call<NdJson<AllergyIntolerance>> mockCall = mock(Call.class);
        NdJson<AllergyIntolerance> ndJson = mock(NdJson.class);
        List listOfAllergyIntoleranceResources = mock(List.class);
        when(ndJson.getResources()).thenReturn(listOfAllergyIntoleranceResources);

        when(mockCall.execute()).thenAnswer(invocation -> Response.success(ndJson));

        when(bulkApiClient.getAllergyIntoleranceResource(url, "bearer " + accessToken)).thenReturn(mockCall);

        subject.process(exports, sourceSystem);


        String authorization = "bearer " + accessToken;
        verify(bulkApiClient).getAllergyIntoleranceResource(url, authorization);
        verify(allergyIntoleranceDataManager).save(listOfAllergyIntoleranceResources);
    }
}