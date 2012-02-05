package com.forrst.api.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Stat implements Serializable {
    
    private int rateLimit;
    private String callsMade;

    public int getRateLimit() {
        return rateLimit;
    }
    
    public void setRateLimit(int rateLimit) {
        this.rateLimit = rateLimit;
    }
    
    public String getCallsMade() {
        return callsMade;
    }
    
    public void setCallsMade(String callsMade) {
        this.callsMade = callsMade;
    }

}
