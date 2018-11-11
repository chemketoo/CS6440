package edu.gatech.curator.service;

import edu.gatech.curator.entity.SourceSystem;
import edu.gatech.curator.fhir.model.ExportOutput;
import edu.gatech.curator.repository.SourceSystemsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuratorServiceTest {

    @MockBean
    private SourceSystemService sourceSystemService;

    @MockBean
    private SourceSystemClient sourceSystemClient;

    @MockBean
    private SourceSystemsRepository sourceSystemRepository;

    @MockBean
    private FhirResourceProcessorService resourceProcessor;

    @Autowired
    private CuratorService subject;

    private SourceSystem expiredSourceSystem1;
    private SourceSystem expiredSourceSystem2;
    private String accessToken1;
    private String accessToken2;
    private URL sourceSystemExportStatusUrl1;
    private URL sourceSystemExportStatusUrl2;
    private List<ExportOutput> exportedResources1;
    private List<ExportOutput> exportedResources2;

    @Before
    public void setUp() throws MalformedURLException {

        expiredSourceSystem1 = mock(SourceSystem.class);
        expiredSourceSystem2 = mock(SourceSystem.class);

        List<SourceSystem> sourceSystems = new ArrayList<SourceSystem>() {{
            add(expiredSourceSystem1);
            add(expiredSourceSystem2);
        }};
        when(sourceSystemService.retrieveSourceSystemPastDemarcationDate()).thenReturn(sourceSystems);

        accessToken1 = "access_token_1_in_jwt_format";
        accessToken2 = "access_token_2_in_jwt_format";
        when(sourceSystemClient.getAccessToken(expiredSourceSystem1)).thenReturn(accessToken1);
        when(sourceSystemClient.getAccessToken(expiredSourceSystem2)).thenReturn(accessToken2);

        sourceSystemExportStatusUrl1 = new URL("http://test1.local");
        sourceSystemExportStatusUrl2 = new URL("http://test2.local");
        when(sourceSystemClient.startPatientExportOperation(expiredSourceSystem1)).thenReturn(sourceSystemExportStatusUrl1);
        when(sourceSystemClient.startPatientExportOperation(expiredSourceSystem2)).thenReturn(sourceSystemExportStatusUrl2);

        exportedResources1 = mock(List.class);
        exportedResources2 = mock(List.class);
        when(sourceSystemClient.getExportOutputs(sourceSystemExportStatusUrl1, expiredSourceSystem1)).thenReturn(exportedResources1);
        when(sourceSystemClient.getExportOutputs(sourceSystemExportStatusUrl2, expiredSourceSystem2)).thenReturn(exportedResources2);
    }

    @Test
    public void start_retrievesSourceSystemsToCurate() {
        subject.start();

        verify(sourceSystemService).retrieveSourceSystemPastDemarcationDate();
    }

    @Test
    public void start_retrieveAccessTokensFromExpiredSourceSystems() {
        subject.start();

        verify(sourceSystemClient).getAccessToken(expiredSourceSystem1);
        verify(sourceSystemClient).getAccessToken(expiredSourceSystem2);
    }

    @Test
    public void start_savesAccessTokensForEachTokenFetchedForSourceSystem() {
        subject.start();

        verify(expiredSourceSystem1).setAccessToken(accessToken1);
        verify(expiredSourceSystem2).setAccessToken(accessToken2);

        verify(sourceSystemRepository).save(expiredSourceSystem1);
        verify(sourceSystemRepository).save(expiredSourceSystem2);
    }

    @Test
    public void start_shouldInvokeSourceSystemClient_startPatientExportOperation() throws MalformedURLException {
        subject.start();

        verify(sourceSystemClient).startPatientExportOperation(expiredSourceSystem1);
        verify(sourceSystemClient).startPatientExportOperation(expiredSourceSystem1);
    }

    @Test
    public void start_shouldPollStatusOfPatientExportOperation_returnsOperationOutputs() throws MalformedURLException {
        subject.start();

        verify(sourceSystemClient).getExportOutputs(sourceSystemExportStatusUrl1, expiredSourceSystem1);
        verify(sourceSystemClient).getExportOutputs(sourceSystemExportStatusUrl2, expiredSourceSystem2);
    }

    @Test
    public void start_processOutputResourceTypesWhenExportOperationIsComplete() {
        subject.start();

        verify(resourceProcessor).process(exportedResources1, expiredSourceSystem1);
        verify(resourceProcessor).process(exportedResources2, expiredSourceSystem2);
    }

}