package com.nitindhar.forrst.model;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class Comment implements Serializable {

    private int id;
    private String userName;
    private String body;
    private Timestamp createdAt;
    private String userIconUrl;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    public String getUserIconUrl() {
        return userIconUrl;
    }
    public void setUserIconUrl(String userIconUrl) {
        this.userIconUrl = userIconUrl;
    }

}