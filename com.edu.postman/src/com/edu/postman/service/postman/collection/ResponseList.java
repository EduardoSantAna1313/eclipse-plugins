package com.edu.postman.service.postman.collection;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author Eduardo
 */
public class ResponseList {

    @SerializedName("collections")
    private List<CollectionList> collections;

    public List<CollectionList> getCollections() {
        return collections;
    }

    public void setCollections(final List<CollectionList> collections) {
        this.collections = collections;
    }

    public CollectionList getCollectionByName(final String name) {
        return this.collections.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ResponseList [collections=");
        builder.append(collections);
        builder.append("]");
        return builder.toString();
    }

}
