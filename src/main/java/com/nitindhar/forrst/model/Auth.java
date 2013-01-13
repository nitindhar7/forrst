package com.nitindhar.forrst.model;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Auth extends AbstractData {

    @JsonProperty("token")
    @NotNull
    private final String accessToken;

    @JsonProperty("user_id")
    @NotNull
    private final int userId;

    @JsonCreator
    public Auth(@JsonProperty("token") @NotNull String accessToken,
                @JsonProperty("user_id") @NotNull int userId) {
        this.accessToken = accessToken;
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public int getUserId() {
        return userId;
    }

}