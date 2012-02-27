package com.nitindhar.forrst.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Stat implements Serializable {

    private static final long serialVersionUID = 602943778915558047L;

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
