package edu.gatech.curator.client;

import edu.gatech.curator.fhir.model.AccessTokenResponse;
import retrofit2.Call;
import retrofit2.http.Field;

public interface BulkFhirApiClient {
    Call<AccessTokenResponse> createAccessToken(
            @Field("scope") String scope,
            @Field("grant_type") String grantType,
            @Field("client_assertion_type") String clientAssertionType,
            @Field("client_assertion") String clientAssertion);
}
