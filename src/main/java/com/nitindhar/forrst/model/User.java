package com.nitindhar.forrst.model;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class User extends AbstractData {

    @JsonProperty("id")
    @NotNull
    private final int id;

    @JsonProperty("username")
    @NotNull
    private final String username;

    @JsonProperty("name")
    @NotNull
    private final String name;

    @JsonProperty("url")
    @NotNull
    private final String url;

    @JsonProperty("posts")
    @NotNull
    private final int posts;

    @JsonProperty("comments")
    @NotNull
    private final int comments;

    @JsonProperty("likes")
    @NotNull
    private final int likes;

    @JsonProperty("followers")
    @NotNull
    private final int followers;

    @JsonProperty("following")
    @NotNull
    private final int following;

    @JsonProperty("photos")
    private final Photo photo;

    @JsonProperty("bio")
    private final String bio;

    @JsonProperty("is_a")
    @NotNull
    private final String isA;

    @JsonProperty("homepage_url")
    private final String homepageUrl;

    @JsonProperty("twitter")
    private final String twitter;

    @JsonProperty("in_directory")
    @NotNull
    private final boolean inDirectory;

    @JsonProperty("tag_string")
    private final String tagString;

    @JsonCreator
    public User(@JsonProperty("id") @NotNull int id,
                @JsonProperty("username") @NotNull String username,
                @JsonProperty("name") @NotNull String name,
                @JsonProperty("url") @NotNull String url,
                @JsonProperty("posts") @NotNull int posts,
                @JsonProperty("comments") @NotNull int comments,
                @JsonProperty("likes") @NotNull int likes,
                @JsonProperty("followers") @NotNull int followers,
                @JsonProperty("following") @NotNull int following,
                @JsonProperty("photos") Photo photo,
                @JsonProperty("bio") String bio,
                @JsonProperty("is_a") @NotNull String isA,
                @JsonProperty("homepage_url") String homepageUrl,
                @JsonProperty("twitter") String twitter,
                @JsonProperty("in_directory") @NotNull boolean inDirectory,
                @JsonProperty("tag_string") String tagString) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.url = url;
        this.posts = posts;
        this.comments = comments;
        this.likes = likes;
        this.followers = followers;
        this.following = following;
        this.photo = photo;
        this.bio = bio;
        this.isA = isA;
        this.homepageUrl = homepageUrl;
        this.twitter = twitter;
        this.inDirectory = inDirectory;
        this.tagString = tagString;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getPosts() {
        return posts;
    }

    public int getComments() {
        return comments;
    }

    public int getLikes() {
        return likes;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public Photo getPhoto() {
        return photo;
    }

    public String getBio() {
        return bio;
    }

    public String getIsA() {
        return isA;
    }

    public String getHomepageUrl() {
        return homepageUrl;
    }

    public String getTwitter() {
        return twitter;
    }

    public boolean isInDirectory() {
        return inDirectory;
    }

    public String getTagString() {
        return tagString;
    }

}