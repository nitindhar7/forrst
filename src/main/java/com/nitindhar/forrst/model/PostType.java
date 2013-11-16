package com.nitindhar.forrst.model;

public enum PostType {

    CODE("code"),
    LINK("link"),
    QUESTION("question"),
    SNAP("snap");

    private String key;

    private PostType(String key) {
        this.key = key;
    }

    /**
     * Return the key of this post type
     */
    public String getKey() {
        return key;
    }

    public static PostType getPostTypeByKey(String key) throws Exception {
        PostType postType = null;
        for (PostType type : PostType.values()) {
            if (type.getKey().equals(key)) {
                postType = type;
                break;
            }
        }

        if (postType == null) {
            throw new Exception("PostType not found with key[" + key + "]");
        }

        return postType;
    }

    @Override
    public String toString() {
        return key;
    }

}