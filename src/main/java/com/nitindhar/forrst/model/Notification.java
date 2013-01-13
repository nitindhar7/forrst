package com.nitindhar.forrst.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Notification implements Serializable {

    private static final long serialVersionUID = 6642536797702136330L;

    @JsonProperty("id")
    @NotNull
    private final String id;

    @JsonProperty("timetamp")
    @NotNull
    private final long timestamp;

    @JsonProperty("behavior")
    @NotNull
    private final String behavior;

    @JsonProperty("for_user_id")
    @NotNull
    private final int forUserId;

    @JsonProperty("object_type")
    @NotNull
    private final String objectType;

    @JsonProperty("object_id")
    @NotNull
    private final int objectId;

    @JsonProperty("data")
    @NotNull
    private final NotificationData data;

    @JsonCreator
    public Notification(@JsonProperty("id") @NotNull String id,
                        @JsonProperty("timetamp") @NotNull long timestamp,
                        @JsonProperty("behavior") @NotNull String behavior,
                        @JsonProperty("for_user_id") @NotNull int forUserId,
                        @JsonProperty("object_type") @NotNull String objectType,
                        @JsonProperty("object_id") @NotNull int objectId,
                        @JsonProperty("data") @NotNull NotificationData data) {
        this.id = id;
        this.timestamp = timestamp;
        this.behavior = behavior;
        this.forUserId = forUserId;
        this.objectType = objectType;
        this.objectId = objectId;
        this.data = data;
    }

    public String getId() {
        return id;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public String getBehavior() {
        return behavior;
    }
    public int getForUserId() {
        return forUserId;
    }
    public String getObjectType() {
        return objectType;
    }
    public int getObjectId() {
        return objectId;
    }
    public NotificationData getData() {
        return data;
    }

}