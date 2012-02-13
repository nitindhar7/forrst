package com.forrst.api.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Notification implements Serializable {

    private String id;
    private long timestamp;
    private String behavior;
    private int forUserId;
    private String objectType;
    private int objectId;
    private NotificationData data;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getBehavior() {
        return behavior;
    }
    
    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }
    
    public int getForUserId() {
        return forUserId;
    }
    
    public void setForUserId(int forUserId) {
        this.forUserId = forUserId;
    }
    
    public String getObjectType() {
        return objectType;
    }
    
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }
    
    public int getObjectId() {
        return objectId;
    }
    
    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }
    
    public NotificationData getData() {
        return data;
    }
    
    public void setData(NotificationData data) {
        this.data = data;
    }

}
