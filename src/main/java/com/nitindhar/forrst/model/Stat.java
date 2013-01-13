package com.nitindhar.forrst.model;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Stat extends AbstractData {

    @JsonProperty("rate_limit")
    @NotNull
    private final int rateLimit;

    @JsonProperty("calls_made")
    @NotNull
    private final int callsMade;

    @JsonCreator
    public Stat(@JsonProperty("rate_limit") @NotNull int rateLimit,
                @JsonProperty("calls_made") @NotNull int callsMade) {
        this.rateLimit = rateLimit;
        this.callsMade = callsMade;
    }

    public int getRateLimit() {
        return rateLimit;
    }

    public int getCallsMade() {
        return callsMade;
    }

}