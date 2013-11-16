package com.nitindhar.forrst.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CommentWrapper extends AbstractData {

    @JsonProperty("count")
    @NotNull
    private final int count;

    @JsonProperty("comments")
    @NotNull
    private final List<Comment> comments;

    @JsonCreator
    public CommentWrapper(@JsonProperty("count") @NotNull int count,
                          @JsonProperty("comments") @NotNull List<Comment> comments) {
        this.count = count;
        this.comments = comments;
    }

    public int getCount() {
        return count;
    }

    public List<Comment> getComments() {
        return comments;
    }

}