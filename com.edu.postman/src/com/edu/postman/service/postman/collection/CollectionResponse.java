/*
 * COPYRIGHT...
 */
package com.edu.postman.service.postman.collection;

import java.util.List;

/**
 * Class to .
 *
 * @author Eduardo
 */
public class CollectionResponse {

    /**
     * List<Collection> - collections.
     */
    private List<Collection> collections;

    /**
     * New instance of CollectionResponse
     */
    public CollectionResponse() {
        // NA
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(final List<Collection> collections) {
        this.collections = collections;
    }

}
