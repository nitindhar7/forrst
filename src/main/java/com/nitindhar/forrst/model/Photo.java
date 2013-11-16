package com.nitindhar.forrst.model;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Photo extends AbstractData {

    @JsonProperty("xl_url")
    @NotNull
    private final String xlUrl;

    @JsonProperty("large_url")
    @NotNull
    private final String largeUrl;

    @JsonProperty("medium_url")
    @NotNull
    private final String mediumUrl;

    @JsonProperty("small_url")
    @NotNull
    private final String smallUrl;

    @JsonProperty("thumb_url")
    @NotNull
    private final String thumbUrl;

    @JsonCreator
    public Photo(@JsonProperty("xl_url") @NotNull String xlUrl,
                 @JsonProperty("large_url") @NotNull String largeUrl,
                 @JsonProperty("medium_url") @NotNull String mediumUrl,
                 @JsonProperty("small_url") @NotNull String smallUrl,
                 @JsonProperty("thumb_url") @NotNull String thumbUrl) {
        this.xlUrl = xlUrl;
        this.largeUrl = largeUrl;
        this.mediumUrl = mediumUrl;
        this.smallUrl = smallUrl;
        this.thumbUrl = thumbUrl;
    }

    public String getXlUrl() {
        return xlUrl;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public String getMediumUrl() {
        return mediumUrl;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

}