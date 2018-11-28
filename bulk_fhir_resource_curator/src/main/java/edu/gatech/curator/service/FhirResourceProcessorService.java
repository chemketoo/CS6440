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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public class FhirResourceProcessorService {

    @Autowired
    private RetrofitClientFactory clientFactory;

    @Autowired
    private AllergyIntoleranceDataManager allergyIntoleranceDataManager;

    @Autowired
    private CarePlanDataManager carePlanDataManager;

    public void process(List<ExportOutputResponse.ExportOutput> exportOutputs, SourceSystem sourceSystem) throws IOException {
        BulkFhirApiClient apiClient = clientFactory.getAPIClient(sourceSystem);

        for (ExportOutputResponse.ExportOutput e :
                exportOutputs) {
            HttpUrl url = HttpUrl.parse(e.getUrl());
            String authorization = "bearer " + sourceSystem.getAccessToken();

            switch (FHIR_RESOURCE_MAP.get(e.getType())) {
                case ALLERGY_INTOLERANCE:
                    Call<NdJson<AllergyIntolerance>> allergyIntoleranceCall = apiClient.getAllergyIntoleranceResource(url, authorization);
                    Response<NdJson<AllergyIntolerance>> allergyIntoleranceResponse = allergyIntoleranceCall.execute();
                    allergyIntoleranceDataManager.save(allergyIntoleranceResponse.body().getResources());
                    break;
                case CARE_PLAN:
                    Call<NdJson<CarePlan>> carePlanCall = apiClient.getCarePlanResources(url, authorization);
                    Response<NdJson<CarePlan>> carePlanResponse = carePlanCall.execute();
                    carePlanDataManager.save(carePlanResponse.body().getResources());
                    break;
                case CLAIM:
                    break;
                case CONDITION:
                    break;
                case DIAGNOSTIC_REPORT:
                    break;
                case ENCOUNTER:
                    break;
                case GOAL:
                    break;
                case IMAGING_STUDY:
                    break;
                case IMMUNIZATION:
                    break;
                case MEDICATION_REQUEST:
                    break;
                case OBSERVATION:
                    break;
                case ORGANIZATION:
                    break;
                case PATIENT:
                    break;
                case PROCEDURE:
                    break;
            }
        }
    }

    private enum FhirResourceType {
        ALLERGY_INTOLERANCE,
        CARE_PLAN,
        CLAIM,
        CONDITION,
        DIAGNOSTIC_REPORT,
        ENCOUNTER,
        GOAL,
        IMAGING_STUDY,
        IMMUNIZATION,
        MEDICATION_REQUEST,
        OBSERVATION,
        ORGANIZATION,
        PATIENT,
        PROCEDURE
    }

    private static HashMap<String, FhirResourceType> FHIR_RESOURCE_MAP = new HashMap<String, FhirResourceType>(){{
       put("AllergyIntolerance", FhirResourceType.ALLERGY_INTOLERANCE);
       put("CarePlan", FhirResourceType.CARE_PLAN);
       put("Claim", FhirResourceType.CLAIM);
       put("Condition", FhirResourceType.CONDITION);
       put("DiagnosticReport", FhirResourceType.DIAGNOSTIC_REPORT);
       put("Encounter", FhirResourceType.ENCOUNTER);
       put("Goal", FhirResourceType.GOAL);
       put("ImagingStudy", FhirResourceType.IMAGING_STUDY);
       put("Immunization", FhirResourceType.IMMUNIZATION);
       put("MedicationRequest", FhirResourceType.MEDICATION_REQUEST);
       put("Observation", FhirResourceType.OBSERVATION);
       put("Organization", FhirResourceType.ORGANIZATION);
       put("Patient", FhirResourceType.PATIENT);
       put("Procedure", FhirResourceType.PROCEDURE);
    }};
}
