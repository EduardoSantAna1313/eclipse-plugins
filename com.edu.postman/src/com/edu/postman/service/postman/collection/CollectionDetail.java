package com.edu.postman.service.postman.collection;

/**
 * @author Eduardo
 */
public class CollectionDetail {

    private Collection collection;

    /**
     * New instance of CollectionDetail
     */
    public CollectionDetail() {
        super();
    }

    /**
     * Copy constructor.
     *
     * @param detail
     */
    public CollectionDetail(final CollectionDetail detail) {
        super();

        this.setCollection(new Collection(detail.getCollection()));
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(final Collection collection) {
        this.collection = collection;
    }

}
