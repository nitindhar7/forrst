package com.nitindhar.forrst.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Post extends AbstractData {

    @JsonProperty("id")
    @NotNull
    private final int id;

    @JsonProperty("tiny_id")
    @NotNull
    private final String tinyId;

    @JsonProperty("post_type")
    @NotNull
    private final String postType;

    @JsonProperty("post_url")
    @NotNull
    private final String postUrl;

    @JsonProperty("created_at")
    @NotNull
    private final String createdAt;

    @JsonProperty("updated_at")
    @NotNull
    private final String updatedAt;

    @JsonProperty("user")
    @NotNull
    private final User user;

    @JsonProperty("published")
    @NotNull
    private final boolean isPublished;

    @JsonProperty("public")
    @NotNull
    private final boolean isPublic;

    @JsonProperty("title")
    @NotNull
    private final String title;

    @JsonProperty("url")
    private final String url;

    @JsonProperty("content")
    private final String content;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("view_count")
    @NotNull
    private final int viewCount;

    @JsonProperty("like_count")
    @NotNull
    private final int likeCount;

    @JsonProperty("comment_count")
    @NotNull
    private final int commentCount;

    @JsonProperty("tag_string")
    @NotNull
    private final String tagString;

    @JsonProperty("snaps")
    private final Snap snap;

    @JsonProperty("multiposts")
    private final List<Multipost> multiposts;

    @JsonCreator
    public Post(@JsonProperty("id") @NotNull int id,
                @JsonProperty("tiny_id") @NotNull String tinyId,
                @JsonProperty("post_type") @NotNull String postType,
                @JsonProperty("post_url") @NotNull String postUrl,
                @JsonProperty("created_at") @NotNull String createdAt,
                @JsonProperty("updated_at") @NotNull String updatedAt,
                @JsonProperty("user") @NotNull User user,
                @JsonProperty("published") @NotNull boolean isPublished,
                @JsonProperty("public") @NotNull boolean isPublic,
                @JsonProperty("title") @NotNull String title,
                @JsonProperty("url") final String url,
                @JsonProperty("content") String content,
                @JsonProperty("description") String description,
                @JsonProperty("view_count") @NotNull int viewCount,
                @JsonProperty("like_count") @NotNull int likeCount,
                @JsonProperty("comment_count") @NotNull int commentCount,
                @JsonProperty("tag_string") @NotNull String tagString,
                @JsonProperty("snaps") Snap snap,
                @JsonProperty("multiposts") List<Multipost> multiposts) {
        this.id = id;
        this.tinyId = tinyId;
        this.postType = postType;
        this.postUrl = postUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
        this.isPublished = isPublished;
        this.isPublic = isPublic;
        this.title = title;
        this.url = url;
        this.content = content;
        this.description = description;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.tagString = tagString;
        this.snap = snap;
        this.multiposts = multiposts;
    }

    public int getId() {
        return id;
    }

    public String getTinyId() {
        return tinyId;
    }

    public String getPostType() {
        return postType;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public User getUser() {
        return user;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public String getTagString() {
        return tagString;
    }

    public Snap getSnap() {
        return snap;
    }

    public List<Multipost> getMultiposts() {
        return multiposts;
    }

}