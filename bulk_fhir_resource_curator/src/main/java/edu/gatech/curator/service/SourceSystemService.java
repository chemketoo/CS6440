package edu.gatech.curator.service;

import edu.gatech.curator.client.BulkFhirApiClient;
import edu.gatech.curator.entity.SourceSystem;
import edu.gatech.curator.factory.RetrofitClientFactory;
import edu.gatech.curator.fhir.model.AccessTokenResponse;
import edu.gatech.curator.fhir.model.ExportOutput;
import edu.gatech.curator.provider.ClientAssertionProvider;
import edu.gatech.curator.provider.DateProvider;
import edu.gatech.curator.repository.SourceSystemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Service
public class SourceSystemService {

    @Autowired
    private SourceSystemsRepository sourceSystemsRepository;

    @Autowired
    private DateProvider dateProvider;

    @Autowired
    ClientAssertionProvider clientAssertionProvider;

    @Autowired
    private RetrofitClientFactory retrofitClientFactory;

    public List<SourceSystem> retrieveSourceSystemPastDemarcationDate() {
        return sourceSystemsRepository.findAllByLastUpdatedBefore(dateProvider.oneWeekAgo());
    }

    public String getAccessToken(SourceSystem sourceSystem) throws IOException {
        String clientAssertion = clientAssertionProvider.create(sourceSystem);
        BulkFhirApiClient apiClient = retrofitClientFactory.getAPIClient(sourceSystem);
        Call<AccessTokenResponse> call = apiClient.createAccessToken(
                "system/*.read",
                "client_credentials",
                "urn:ietf:params:oauth:client-assertion-type:jwt-bearer",
                clientAssertion);

        return call.execute().body().getAccessToken();
    }

    public URL startPatientExportOperation(SourceSystem sourceSystem) throws MalformedURLException {
        return new URL("https://example.net");
    }

    public List<ExportOutput> getExportOutputs(URL url, SourceSystem sourceSystem) {
        return new ArrayList<ExportOutput>();
    }

}
