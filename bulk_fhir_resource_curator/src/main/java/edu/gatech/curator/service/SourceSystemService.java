package edu.gatech.curator.service;

import edu.gatech.curator.client.BulkFhirApiClient;
import edu.gatech.curator.entity.SourceSystem;
import edu.gatech.curator.factory.RetrofitClientFactory;
import edu.gatech.curator.fhir.model.AccessTokenResponse;
import edu.gatech.curator.fhir.model.ExportOutput;
import edu.gatech.curator.fhir.model.OperationOutcome;
import edu.gatech.curator.provider.ClientAssertionProvider;
import edu.gatech.curator.provider.DateProvider;
import edu.gatech.curator.provider.OperationOutcomeTextUrlProvider;
import edu.gatech.curator.repository.SourceSystemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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

    @Autowired
    private OperationOutcomeTextUrlProvider operationOutcomeTextUrlProvider;

    public List<SourceSystem> retrieveSourceSystemPastDemarcationDate() {
        return sourceSystemsRepository.findAllByLastUpdatedBefore(dateProvider.oneWeekAgo());
    }

    public String getAccessToken(SourceSystem sourceSystem) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        String clientAssertion = clientAssertionProvider.create(sourceSystem);
        BulkFhirApiClient apiClient = retrofitClientFactory.getAPIClient(sourceSystem);

        Call<AccessTokenResponse> call = apiClient.createAccessToken(
                "system/*.read",
                "client_credentials",
                "urn:ietf:params:oauth:client-assertion-type:jwt-bearer",
                clientAssertion);

        Response<AccessTokenResponse> response = call.execute();
        AccessTokenResponse body = response.body();
        return body.getAccessToken();
    }

    public URL startPatientExportOperation(SourceSystem sourceSystem) throws IOException {
        BulkFhirApiClient apiClient = retrofitClientFactory.getAPIClient(sourceSystem);
        String authorization = "bearer " + sourceSystem.getAccessToken();
        Call<OperationOutcome> call = apiClient.startPatientExportOperation(
                authorization,
                "application/fhir+ndjson");
        Response<OperationOutcome> response = call.execute();
        OperationOutcome outcome = response.body();
        return operationOutcomeTextUrlProvider.parse(outcome.getText().getDiv());
    }

    public List<ExportOutput> getExportOutputs(URL url, SourceSystem sourceSystem) {
        return new ArrayList<ExportOutput>();
    }

}
