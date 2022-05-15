package com.edu.postman.service.postman.collection;

import com.google.gson.annotations.SerializedName;

/**
 * @author Eduardo
 */
public class ResponseUpdate {

    @SerializedName("collection")
    private UpdateCollection collection;

    public UpdateCollection getCollection() {
        return collection;
    }

    public void setCollection(final UpdateCollection collection) {
        this.collection = collection;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ResponseUpdate [collection=");
        builder.append(collection);
        builder.append("]");
        return builder.toString();
    }

}
