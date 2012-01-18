package com.forrst.api.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Photo implements Serializable {
    
    private String xlUrl;
    private String largeUrl;
    private String mediumUrl;
    private String smallUrl;
    private String thumbUrl;
    
    public String getXlUrl() {
        return xlUrl;
    }
    public void setXlUrl(String xlUrl) {
        this.xlUrl = xlUrl;
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

}
