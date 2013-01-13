package com.nitindhar.forrst.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Comment extends AbstractData {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @JsonProperty("id")
    @NotNull
    private final int id;

    @JsonProperty("user")
    @NotNull
    private final User user;

    @JsonProperty("body")
    @NotNull
    private final String body;

    @JsonProperty("created_at")
    @NotNull
    private final String createdAt;

    @JsonProperty("updated_at")
    @NotNull
    private final String updatedAt;

    @JsonProperty("replies")
    private final List<Comment> replies;

    @JsonCreator
    public Comment(@JsonProperty("id") @NotNull int id,
                   @JsonProperty("user") @NotNull User user,
                   @JsonProperty("body") @NotNull String body,
                   @JsonProperty("created_at") @NotNull String createdAt,
                   @JsonProperty("updated_at") @NotNull String updatedAt,
                   @JsonProperty("replies") List<Comment> replies) {
        this.id = id;
        this.user = user;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.replies = replies;
    }

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public User getUser() {
        return user;
    }

    public Timestamp getCreatedAt() throws ParseException {
        return new Timestamp(format.parse(createdAt).getTime());
    }

    public Timestamp getUpdatedAt() throws ParseException {
        return new Timestamp(format.parse(updatedAt).getTime());
    }

    public List<Comment> getReplies() {
        return replies;
    }

}