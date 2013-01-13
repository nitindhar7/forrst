package com.nitindhar.forrst.model;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class NotificationData extends AbstractData {

    @JsonProperty("actor")
    @NotNull
    private final String actor;

    @JsonProperty("actor_url")
    @NotNull
    private final String actorUrl;

    @JsonProperty("object_url")
    @NotNull
    private final String objectUrl;

    @JsonProperty("post_type")
    private final String postType;

    @JsonProperty("post_title")
    private final String postTitle;

    @JsonProperty("photo")
    private final String photo;

    @JsonCreator
    public NotificationData(@JsonProperty("actor") @NotNull String actor,
                            @JsonProperty("actor_url") @NotNull String actorUrl,
                            @JsonProperty("object_url") @NotNull String objectUrl,
                            @JsonProperty("post_type") String postType,
                            @JsonProperty("post_title") String postTitle,
                            @JsonProperty("photo") String photo) {
        this.actor = actor;
        this.actorUrl = actorUrl;
        this.objectUrl = objectUrl;
        this.postType = postType;
        this.postTitle = postTitle;
        this.photo = photo;
    }

    public String getActor() {
        return actor;
    }

    public String getActorUrl() {
        return actorUrl;
    }

    public String getObjectUrl() {
        return objectUrl;
    }

    public String getPostType() {
        return postType;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPhoto() {
        return photo;
    }

}