package edu.gatech.curator.fhir.model;

public class AccessTokenResponse {
    private String tokenType;
    private int expiresIn;
    private String accessToken;

    public AccessTokenResponse(String tokenType, int expiresIn, String accessToken) {
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
