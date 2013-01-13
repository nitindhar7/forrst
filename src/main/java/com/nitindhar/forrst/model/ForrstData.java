package com.nitindhar.forrst.model;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ForrstData<T> extends AbstractData {

    @JsonProperty("resp")
    @NotNull
    private final T resp;

    @JsonProperty("stat")
    @NotNull
    private final String stat;

    @JsonProperty("in")
    @NotNull
    private final Float in;

    @JsonProperty("authed")
    @NotNull
    private final Boolean authed;

    @JsonProperty("env")
    @NotNull
    private final String env;

    @JsonCreator
    public ForrstData(@JsonProperty("resp") @NotNull T resp,
                    @JsonProperty("stat") @NotNull String stat,
                    @JsonProperty("in") @NotNull Float in,
                    @JsonProperty("authed") @NotNull Boolean authed,
                    @JsonProperty("env") @NotNull String env) {
        this.resp = resp;
        this.stat = stat;
        this.in = in;
        this.authed = authed;
        this.env = env;
    }

    public T getResp() {
        return resp;
    }

    public String getStat() {
        return stat;
    }

    public Float getIn() {
        return in;
    }

    public Boolean getAuthed() {
        return authed;
    }

    public String getEnv() {
        return env;
    }

}