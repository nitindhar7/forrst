package com.nitindhar.forrst.model;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Multipost extends AbstractData {

    @JsonProperty("position")
    @NotNull
    private final int position;

    @JsonProperty("type")
    @NotNull
    private final String type;

    @JsonProperty("content")
    private final String content;

    @JsonProperty("formatted_content")
    private final String formattedContent;

    @JsonProperty("title")
    private final String title;

    @JsonProperty("url")
    private final String url;

    @JsonProperty("snaps")
    private final Snap snap;

    @JsonCreator
    public Multipost(@JsonProperty("position") @NotNull int position,
            @JsonProperty("type") @NotNull String type,
            @JsonProperty("content") String content,
            @JsonProperty("formatted_content") String formattedContent,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url,
            @JsonProperty("snaps") Snap snap) {
        this.position = position;
        this.type = type;
        this.content = content;
        this.formattedContent = formattedContent;
        this.title = title;
        this.url = url;
        this.snap = snap;
    }

    public int getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public String getFormattedContent() {
        return formattedContent;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Snap getSnap() {
        return snap;
    }

}