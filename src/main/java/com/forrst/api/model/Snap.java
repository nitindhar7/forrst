package com.forrst.api.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Snap implements Serializable {
    
    private String megaUrl;
    private String keithUrl;
    private String largeUrl;
    private String mediumUrl;
    private String smallUrl;
    private String thumbUrl;
    private String originalUrl;

    public String getMegaUrl() {
        return megaUrl;
    }
    public void setMegaUrl(String megaUrl) {
        this.megaUrl = megaUrl;
    }
    public String getKeithUrl() {
        return keithUrl;
    }
    public void setKeithUrl(String keithUrl) {
        this.keithUrl = keithUrl;
    }
    public String getLargeUrl() {
        return largeUrl;
    }
    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }
    public String getMediumUrl() {
        return mediumUrl;
    }
    public void setMediumUrl(String mediumUrl) {
        this.mediumUrl = mediumUrl;
    }
    public String getSmallUrl() {
        return smallUrl;
    }
    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }
    public String getThumbUrl() {
        return thumbUrl;
    }
    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }
    public String getOriginalUrl() {
        return originalUrl;
    }
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

}
