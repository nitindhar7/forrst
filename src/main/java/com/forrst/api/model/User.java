package com.forrst.api.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
    
    private int id;
    private String username;
    private String name;
    private String url;
    private int posts;
    private int comments;
    private int likes;
    private int followers;
    private int following;
    private Photo photo;
    private String bio;
    private String isA;
    private String homepageUrl;
    private String twitter;
    private boolean inDirectory;
    private String tagString;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public int getPosts() {
        return posts;
    }
    public void setPosts(int posts) {
        this.posts = posts;
    }
    public int getComments() {
        return comments;
    }
    public void setComments(int comments) {
        this.comments = comments;
    }
    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }
    public int getFollowers() {
        return followers;
    }
    public void setFollowers(int followers) {
        this.followers = followers;
    }
    public int getFollowing() {
        return following;
    }
    public void setFollowing(int following) {
        this.following = following;
    }
    public Photo getPhoto() {
        return photo;
    }
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public String getIsA() {
        return isA;
    }
    public void setIsA(String isA) {
        this.isA = isA;
    }
    public String getHomepageUrl() {
        return homepageUrl;
    }
    public void setHomepageUrl(String homepageUrl) {
        this.homepageUrl = homepageUrl;
    }
    public String getTwitter() {
        return twitter;
    }
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
    public boolean isInDirectory() {
        return inDirectory;
    }
    public void setInDirectory(boolean inDirectory) {
        this.inDirectory = inDirectory;
    }
    public String getTagString() {
        return tagString;
    }
    public void setTagString(String tagString) {
        this.tagString = tagString;
    }

}
