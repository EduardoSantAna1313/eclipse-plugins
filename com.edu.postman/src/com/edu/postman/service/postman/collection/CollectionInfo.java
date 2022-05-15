/*
 * COPYRIGHT...
 */
package com.edu.postman.service.postman.collection;

import com.google.gson.annotations.SerializedName;

/**
 * Class to CollectionInfo.
 *
 * @author Eduardo
 */
public class CollectionInfo {

    @SerializedName(value = "_postman_id")
    private String postmanId;

    private String name;

    private String description;

    private String schema;

    /**
     * New instance of CollectionInfo
     */
    public CollectionInfo() {
        super();
    }

    /**
     * Copy Constructor.
     *
     * @param info
     */
    public CollectionInfo(final CollectionInfo info) {
        super();
        this.postmanId = info.getPostmanId();
        this.name = info.getName();
        this.description = info.getDescription();
        this.schema = info.getSchema();
    }

    public String getPostmanId() {
        return postmanId;
    }

    public void setPostmanId(final String postmanId) {
        this.postmanId = postmanId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(final String schema) {
        this.schema = schema;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("CollectionInfo [postmanId=");
        builder.append(postmanId);
        builder.append(", name=");
        builder.append(name);
        builder.append(", schema=");
        builder.append(schema);
        builder.append("]");
        return builder.toString();
    }

}
