package com.nitindhar.forrst.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PostWrapper extends AbstractData {

    @JsonProperty("posts")
    @NotNull
    private final List<Post> posts;

    @JsonCreator
    public PostWrapper(@JsonProperty("posts") @NotNull List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }

}