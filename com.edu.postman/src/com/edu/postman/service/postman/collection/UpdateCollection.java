package com.edu.postman.service.postman.collection;

import com.google.gson.annotations.SerializedName;

/**
 * @author Eduardo
 */
public class UpdateCollection {

    /**
     * String - id.
     */
    @SerializedName("id")
    private String id;

    /**
     * String - name.
     */
    @SerializedName("name")
    private String name;

    /**
     * String - uid.
     */
    @SerializedName("uid")
    private String uid;

    /**
     * Create a new instance of ResponseUpdate
     */
    public UpdateCollection() {
        super();
    }

    /**
     * Retrieve the value of id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Set a new value to id.
     *
     * @param id
     *               the id to set
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Retrieve the value of name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set a new value to name.
     *
     * @param name
     *                 the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Retrieve the value of uid.
     *
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * Set a new value to uid.
     *
     * @param uid
     *                the uid to set
     */
    public void setUid(final String uid) {
        this.uid = uid;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ResponseUpdate [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", uid=");
        builder.append(uid);
        builder.append("]");
        return builder.toString();
    }

}
