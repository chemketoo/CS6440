package edu.gatech.curator.client;

import edu.gatech.curator.fhir.model.AccessTokenResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SmartReferenceImplApi extends BulkFhirApiClient {
    @FormUrlEncoded
    @POST("auth/token")
    Call<AccessTokenResponse> createAccessToken(
            @Field("scope") String scope,
            @Field("grant_type") String grantType,
            @Field("client_assertion_type") String clientAssertionType,
            @Field("client_assertion") String clientAssertion);
}
