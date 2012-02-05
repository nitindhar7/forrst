package com.forrst.api.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Post implements Serializable {
    
    private int id;
    private String tinyId;
    private String postType;
    private String postUrl;
    private String createdAt;
    private String updatedAt;
    private User user;
    private boolean isPublished;
    private boolean isPublic;
    private String title;
    private String url;
    private String content;
    private String description;
    private int viewCount;
    private int likeCount;
    private int commentCount;
    private String tagString;
    private Snap snap;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTinyId() {
        return tinyId;
    }
    public void setTinyId(String tinyId) {
        this.tinyId = tinyId;
    }
    public String getPostType() {
        return postType;
    }
    public void setPostType(String postType) {
        this.postType = postType;
    }
    public String getPostUrl() {
        return postUrl;
    }
    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public String getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public boolean isPublished() {
        return isPublished;
    }
    public void setPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }
    public boolean isPublic() {
        return isPublic;
    }
    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getViewCount() {
        return viewCount;
    }
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
    public int getLikeCount() {
        return likeCount;
    }
    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
    public int getCommentCount() {
        return commentCount;
    }
    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
    public String getTagString() {
        return tagString;
    }
    public void setTagString(String tagString) {
        this.tagString = tagString;
    }
    public Snap getSnap() {
        return snap;
    }
    public void setSnap(Snap snap) {
        this.snap = snap;
    }

}
