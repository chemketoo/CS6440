package edu.gatech.curator.client;

import edu.gatech.curator.fhir.model.AccessTokenResponse;
import edu.gatech.curator.fhir.model.OperationOutcome;
import retrofit2.Call;
import retrofit2.http.*;

public interface BulkFhirApiClient {
    @FormUrlEncoded
    @POST("auth/token")
    Call<AccessTokenResponse> createAccessToken(
            @Field("scope") String scope,
            @Field("grant_type") String grantType,
            @Field("client_assertion_type") String clientAssertionType,
            @Field("client_assertion") String clientAssertion);

    @Headers({
        "Accept: application/fhir+json",
        "Prefer: respond-async"
    })

    @GET("fhir/Patient/$export")
    Call<OperationOutcome> startPatientExportOperation(
            @Header("Authorization") String authorization,
            @Query("_outputFormat") String exportFormat);
}
