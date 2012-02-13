package com.forrst.api.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class NotificationData implements Serializable {
    
    private String actor;
    private String actorUrl;
    private String objectUrl;
    private String postType;
    private String postTitle;
    private String photo;
    
    public String getActor() {
        return actor;
    }
    
    public void setActor(String actor) {
        this.actor = actor;
    }
    
    public String getActorUrl() {
        return actorUrl;
    }
    
    public void setActorUrl(String actorUrl) {
        this.actorUrl = actorUrl;
    }
    
    public String getObjectUrl() {
        return objectUrl;
    }
    
    public void setObjectUrl(String objectUrl) {
        this.objectUrl = objectUrl;
    }
    
    public String getPostType() {
        return postType;
    }
    
    public void setPostType(String postType) {
        this.postType = postType;
    }
    
    public String getPostTitle() {
        return postTitle;
    }
    
    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }
    
    public String getPhoto() {
        return photo;
    }
    
    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
