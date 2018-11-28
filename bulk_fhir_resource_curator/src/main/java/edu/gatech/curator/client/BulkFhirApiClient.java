package edu.gatech.curator.client;

import edu.gatech.curator.model.AccessTokenResponse;
import edu.gatech.curator.model.ExportOutputResponse;
import edu.gatech.curator.model.NdJson;
import edu.gatech.curator.model.OperationOutcomeResponse;
import okhttp3.HttpUrl;
import org.hl7.fhir.dstu3.model.AllergyIntolerance;
import org.hl7.fhir.dstu3.model.CarePlan;
import retrofit2.Call;
import retrofit2.http.*;

public interface BulkFhirApiClient {
    @FormUrlEncoded
    @POST("auth/token")
    Call<AccessTokenResponse> createAccessToken(@Field("scope") String scope,
                                                @Field("grant_type") String grantType,
                                                @Field("client_assertion_type") String clientAssertionType,
                                                @Field("client_assertion") String clientAssertion);
                                                @Headers({
                                                    "Accept: application/fhir+json",
                                                    "Prefer: respond-async"
                                                })

    @GET("fhir/Patient/$export")
    Call<OperationOutcomeResponse> startPatientExportOperation(@Header("Authorization") String authorization,
                                                               @Query("_outputFormat") String exportFormat);

    @GET
    Call<ExportOutputResponse> getExportStatus(@Url HttpUrl url,
                                               @Header("Authorization") String authorization);

    @GET
    Call<NdJson<AllergyIntolerance>> getAllergyIntoleranceResource(@Url HttpUrl url,
                                                                   @Header("Authorization") String authorization);

    @GET
    Call<NdJson<CarePlan>> getCarePlanResources(@Url HttpUrl url,
                                                @Header("Authorization") String authorization);
}
