package edu.gatech.curator.service;

import edu.gatech.curator.client.BulkFhirApiClient;
import edu.gatech.curator.entity.SourceSystem;
import edu.gatech.curator.factory.RetrofitClientFactory;
import edu.gatech.curator.manager.AllergyIntoleranceDataManager;
import edu.gatech.curator.model.ExportOutputResponse;
import edu.gatech.curator.model.NdJson;
import okhttp3.HttpUrl;
import org.hl7.fhir.dstu3.model.AllergyIntolerance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@Service
public class FhirResourceProcessorService {

    @Autowired
    RetrofitClientFactory clientFactory;

    @Autowired
    AllergyIntoleranceDataManager dataManager;

    public void process(List<ExportOutputResponse.ExportOutput> exportOutputs, SourceSystem sourceSystem) throws IOException {
        BulkFhirApiClient apiClient = clientFactory.getAPIClient(sourceSystem);

        for (ExportOutputResponse.ExportOutput e :
                exportOutputs) {
            HttpUrl url = HttpUrl.parse(e.getUrl());
            String authorization = "bearer " + sourceSystem.getAccessToken();

            if (e.getType().equalsIgnoreCase("AllergyIntolerance")) {
                Call<NdJson<AllergyIntolerance>> call =  apiClient.getAllergyIntoleranceResource(url, authorization);
                Response<NdJson<AllergyIntolerance>> response = call.execute();
                dataManager.save(response.body().getResources());
            }
        }
    }
}
