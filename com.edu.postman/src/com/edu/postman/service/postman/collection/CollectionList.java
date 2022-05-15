package com.edu.postman.service.postman.collection;

import com.google.gson.annotations.SerializedName;

/**
 * @author Eduardo
 */
public class CollectionList {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("owner")
    private String owner;

    @SerializedName("uid")
    private String uid;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(final String owner) {
        this.owner = owner;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(final String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("CollectionList [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", owner=");
        builder.append(owner);
        builder.append(", uid=");
        builder.append(uid);
        builder.append("]");
        return builder.toString();
    }

}
