package com.edu.postman.service.postman.collection;

import com.google.gson.annotations.SerializedName;

/**
 * @author Eduardo
 */
public class ResponseDetail {

    /**
     * CollectionDetail - collection.
     */
    @SerializedName("collection")
    private CollectionDetail collection;

    public CollectionDetail getCollection() {
        return collection;
    }

    public void setCollection(final CollectionDetail collection) {
        this.collection = collection;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ResponseDetail [collection=");
        builder.append(collection);
        builder.append("]");
        return builder.toString();
    }

}
