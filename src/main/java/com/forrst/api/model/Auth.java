package com.forrst.api.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Auth implements Serializable {
    
    private String accessToken;
    private int userId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    

}
