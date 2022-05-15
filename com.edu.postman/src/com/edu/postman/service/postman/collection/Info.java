package com.edu.postman.service.postman.collection;

import com.google.gson.annotations.SerializedName;

/**
 * @author Eduardo
 */
public class Info {

    @SerializedName("_postman_id")
    private String postmanId;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("schema")
    private String schema;

    /**
     * Create a new instance of Info
     */
    public Info() {
        super();
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
        builder.append("Info [postmanId=");
        builder.append(postmanId);
        builder.append(", name=");
        builder.append(name);
        builder.append(", description=");
        builder.append(description);
        builder.append(", schema=");
        builder.append(schema);
        builder.append("]");
        return builder.toString();
    }

}
