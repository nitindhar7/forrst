package com.nitindhar.forrst.model;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Snap extends AbstractData {

    @JsonProperty("mega_url")
    private final String megaUrl;

    @JsonProperty("keith_url")
    private final String keithUrl;

    @JsonProperty("large_url")
    private final String largeUrl;

    @JsonProperty("medium_url")
    private final String mediumUrl;

    @JsonProperty("small_url")
    private final String smallUrl;

    @JsonProperty("thumb_url")
    private final String thumbUrl;

    @JsonProperty("original_url")
    private final String originalUrl;

    @JsonCreator
    public Snap(@JsonProperty("mega_url") String megaUrl,
                @JsonProperty("keith_url") String keithUrl,
                @JsonProperty("large_url") String largeUrl,
                @JsonProperty("medium_url") String mediumUrl,
                @JsonProperty("small_url") String smallUrl,
                @JsonProperty("thumb_url") String thumbUrl,
                @JsonProperty("original_url") String originalUrl) {
        this.megaUrl = megaUrl;
        this.keithUrl = keithUrl;
        this.largeUrl = largeUrl;
        this.mediumUrl = mediumUrl;
        this.smallUrl = smallUrl;
        this.thumbUrl = thumbUrl;
        this.originalUrl = originalUrl;
    }

    public String getMegaUrl() {
        return megaUrl;
    }

    public String getKeithUrl() {
        return keithUrl;
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

    public String getOriginalUrl() {
        return originalUrl;
    }

}